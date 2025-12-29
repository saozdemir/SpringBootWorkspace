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

    private final Semaphore semaphore = new Semaphore(800);

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
     * SENARYO 1: VIRTUAL THREAD (Sanal İş Parçacıkları)
     * Semaphore kaldırıldı. Sistem kaynakları elverdiği ölçüde sınırsız paralellik sağlar.
     * Connection Pool limiti (10.000) sayesinde I/O bloklamasında beklemez.
     */
//    @Transactional(readOnly = true)
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread() {
        List<PersonnelDto> personnelList = getAllPersonnel();
        long startTime = System.currentTimeMillis();
        System.out.println("--> VIRTUAL Thread İşlemi Başladı. Adet: " + personnelList.size());

        // Try-with-resources bloğu executor'ı işlem bitince otomatik kapatır.
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            List<CompletableFuture<PersonnelDto>> futures = personnelList.stream()
                    .map(personnel -> CompletableFuture.supplyAsync(() -> {
                        // Her personel için 2 işlemi PARALEL başlatıyoruz (Virtual Thread içinde)
                        CompletableFuture<Void> educationFuture = CompletableFuture.runAsync(() ->
                                personnel.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId())), executorService);

                        CompletableFuture<Void> taskFuture = CompletableFuture.runAsync(() ->
                                personnel.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId())), executorService);

                        // İkisinin de bitmesini bekle (Non-blocking wait)
                        CompletableFuture.allOf(educationFuture, taskFuture).join();
                        return personnel;
                    }, executorService))
                    .collect(Collectors.toList());

            // Tüm personellerin işlemi bitene kadar bekle ve sonucu topla
            List<PersonnelDto> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .join();

            long duration = System.currentTimeMillis() - startTime;
            System.out.println("<-- VIRTUAL Thread Bitti. Süre: " + duration + " ms");
            return result;
        }
    }

    /**
     * SENARYO 2: PLATFORM THREAD (Geleneksel Model)
     * Standart bir Tomcat sunucusunu simüle etmek için 200 thread'lik sabit bir havuz kullanıyoruz.
     * Bu havuz dolduğunda diğer işler kuyrukta bekler (Latency artışı burada gözlemlenir).
     */
//    @Transactional(readOnly = true)
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsPlatformThread() {
        List<PersonnelDto> personnelList = getAllPersonnel();
        long startTime = System.currentTimeMillis();
        System.out.println("--> PLATFORM Thread İşlemi Başladı. Adet: " + personnelList.size());

        // Baseline (Taban) Karşılaştırma için Sabit Havuz (Tomcat Default: 200)
        ExecutorService mainExecutor = Executors.newFixedThreadPool(200);

        try {
            List<CompletableFuture<PersonnelDto>> futures = personnelList.stream()
                    .map(personnel -> CompletableFuture.supplyAsync(() -> {
                        // Alt görevleri de aynı havuzda veya main thread'de yapabiliriz.
                        // Adil olması için burada bloklayarak yapıyoruz (Sequential within thread),
                        // çünkü Platform thread'ler pahalıdır, her alt işe yeni thread açılmaz.
                        try {
                            personnel.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId()));
                            personnel.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return personnel;
                    }, mainExecutor))
                    .collect(Collectors.toList());

            List<PersonnelDto> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .join();

            long duration = System.currentTimeMillis() - startTime;
            System.out.println("<-- PLATFORM Thread Bitti. Süre: " + duration + " ms");
            return result;
        } finally {
            mainExecutor.shutdown(); // Havuzu mutlaka kapatıyoruz
        }
    }


    /**
     * SENARYO 3: VIRTUAL THREAD (Semaphore ile Sınırlama)
     * Semaphore ile aynı anda çalışan sanal thread sayısını sınırlıyoruz. Kaynak Çekişmesi önlemek için.
     */

    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThreadWithSemaphore() {
        List<PersonnelDto> personnelList = getAllPersonnel();

        long startTime = System.currentTimeMillis();
        System.out.println("--> VIRTUAL(Semaphore) Thread İşlemi Başladı. Adet: " + personnelList.size());

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            List<CompletableFuture<PersonnelDto>> futures = personnelList.stream()
                    .map(personnel -> CompletableFuture.supplyAsync(() -> {
                        try {
                            // İzin İste (Bloklanmadan bekle)
                            semaphore.acquire();

                            // --- KRİTİK BÖLGE (İşlem) ---
                            // Education ve Task çağrıları burada yapılır
                            personnel.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId()));
                            personnel.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId()));
                            // -----------------------------

                            return personnel;
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        } finally {
                            // İşi biten izni mutlaka iade eder
                            semaphore.release();
                        }
                    }, executorService))
                    .collect(Collectors.toList());

            // Tüm personellerin işlemi bitene kadar bekle ve sonucu topla
            List<PersonnelDto> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .join();

            long duration = System.currentTimeMillis() - startTime;
            System.out.println("<-- VIRTUAL(Semaphore) Thread Bitti. Süre: " + duration + " ms");
            return result;
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
