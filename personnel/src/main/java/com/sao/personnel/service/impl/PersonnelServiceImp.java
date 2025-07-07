package com.sao.personnel.service.impl;

import com.sao.personnel.dto.EducationDto;
import com.sao.personnel.dto.PersonnelDto;
import com.sao.personnel.dto.TaskDto;
import com.sao.personnel.entity.Personnel;
import com.sao.personnel.integration.IEducationWebService;
import com.sao.personnel.integration.ITaskWebService;
import com.sao.personnel.performance.*;
import com.sao.personnel.repository.PersonnelRepository;
import com.sao.personnel.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Service
public class PersonnelServiceImp implements IPersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private ITaskWebService taskWebService;

    @Autowired
    private IEducationWebService educationWebService;

//    @Autowired
//    private PerformanceTracker tracker;

    // ... diğer metotlar (getAllPersonnel, getPersonnelById vb.) burada yer alıyor ...
    @Override
    public List<PersonnelDto> getAllPersonnel() {
        return personnelRepository.findAll().stream()
                .map(personnel -> {
                    PersonnelDto dto = new PersonnelDto();
                    dto.setId(personnel.getId());
                    dto.setName(personnel.getName());
                    dto.setSurname(personnel.getSurname());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Personnel getPersonnelById(Long id) {
        return personnelRepository.findById(id).orElse(null);
    }

    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetails() {
//        tracker.start();
        List<PersonnelDto> personnelList = getAllPersonnel();
        for (PersonnelDto personnelDto : personnelList) {
            List<EducationDto> educationDtoList = educationWebService.getEducationByPersonnelId(personnelDto.getId());
            personnelDto.setEducations(educationDtoList);

            List<TaskDto> taskDtoList = taskWebService.getTaskByPersonnelId(personnelDto.getId());
            personnelDto.setTasks(taskDtoList);
        }
//        tracker.stop("Sequential Personnel Details Fetch");
        return personnelList;
    }


    /**
     * Sanal thread'ler ve Semaphore kullanarak, eşzamanlılığı kontrol altında tutarak
     * personellerin detaylarını paralel olarak çeker. Bu, kaynakların tükenmesini engeller.
     */
    @Transactional(readOnly = true)
//    @ResourceProfiled
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread() {
//        tracker.start();
        List<PersonnelDto> personnelList = getAllPersonnel();

        // Aynı anda en fazla 200 isteğin aktif olmasına izin veriyoruz.
        // Bu "trafik polisi", işletim sistemi kaynaklarının tükenmesini engeller.
        // Bu sayıyı sisteminizin ve hedef servislerin kapasitesine göre artırıp azaltabilirsiniz.
        final Semaphore semaphore = new Semaphore(200);

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            List<CompletableFuture<PersonnelDto>> futures = personnelList.stream()
                    .map(personnel -> {
                        try {
                            // Görevi başlatmadan önce semafor'dan bir "izin" alıyoruz.
                            // Eğer 200 izin de kullanımdaysa, bu satırda yeni bir izin boşalana kadar bekler.
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }

                        return CompletableFuture.supplyAsync(() -> {
                            try {
                                // Her personel için iki web servisi çağrısını aynı anda başlatıyoruz.
                                CompletableFuture<Void> educationFuture = CompletableFuture.runAsync(() ->
                                        personnel.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId())), executorService);

                                CompletableFuture<Void> taskFuture = CompletableFuture.runAsync(() ->
                                        personnel.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId())), executorService);

                                CompletableFuture.allOf(educationFuture, taskFuture).join();
                                return personnel;
                            } finally {
                                // Görev başarıyla tamamlansa da, hata alsa da,
                                // aldığımız "izni" mutlaka geri veriyoruz ki başka bir görev kullanabilsin.
                                semaphore.release();
                            }
                        }, executorService);
                    })
                    .collect(Collectors.toList());

            // Tüm personeller için başlatılan asenkron işlemlerin tamamlanmasını bekleyip sonuçları topluyoruz.
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .join();
        } finally {
//            tracker.stop("Virtual Thread Personnel Details Fetch");
        }
    }

    /**
     * Platform thread'leri kullanarak personellerin detaylarını paralel olarak çeker.
     * Deadlock'u önlemek için ana ve alt görevler için ayrı havuzlar kullanılır.
     */
    @Transactional(readOnly = true)
//    @ResourceProfiled
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsPlatformThread() {
//        tracker.start();
        List<PersonnelDto> personnelList = getAllPersonnel();

        int poolSize = Runtime.getRuntime().availableProcessors() * 10;
        ExecutorService mainExecutor = Executors.newFixedThreadPool(poolSize);
        ExecutorService subTaskExecutor = Executors.newFixedThreadPool(poolSize);

        try {
            List<CompletableFuture<PersonnelDto>> futures = personnelList.stream()
                    .map(personnel -> CompletableFuture.supplyAsync(() -> {
                        CompletableFuture<Void> educationFuture = CompletableFuture.runAsync(() ->
                                personnel.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId())), subTaskExecutor);

                        CompletableFuture<Void> taskFuture = CompletableFuture.runAsync(() ->
                                personnel.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId())), subTaskExecutor);

                        CompletableFuture.allOf(educationFuture, taskFuture).join();
                        return personnel;
                    }, mainExecutor))
                    .collect(Collectors.toList());

            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .join();
        } finally {
            mainExecutor.shutdown();
            subTaskExecutor.shutdown();
//            tracker.stop("Platform Thread Personnel Details Fetch");
        }
    }

    @Override
    public PersonnelDto getPersonnelDetails(Long personnelId) {
//        tracker.start();
        Personnel personnel = getPersonnelById(personnelId);
        if (personnel == null) {
            return null;
        }

        PersonnelDto personnelDto = new PersonnelDto();
        personnelDto.setId(personnel.getId());
        personnelDto.setName(personnel.getName());
        personnelDto.setSurname(personnel.getSurname());

        List<EducationDto> educationDto = educationWebService.getEducationByPersonnelId(personnelId);
        List<TaskDto> taskDto = taskWebService.getTaskByPersonnelId(personnelId);

        personnelDto.setEducations(educationDto);
        personnelDto.setTasks(taskDto);

//        tracker.stop("Single Personnel Details Fetch");
        return personnelDto;
    }
}
