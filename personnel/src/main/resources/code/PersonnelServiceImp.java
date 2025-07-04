package com.sao.personnel.service.impl;

import com.sao.personnel.dto.EducationDto;
import com.sao.personnel.dto.PersonnelDto;
import com.sao.personnel.dto.TaskDto;
import com.sao.personnel.entity.Personnel;
import com.sao.personnel.integration.IEducationWebService;
import com.sao.personnel.integration.ITaskWebService;
import com.sao.personnel.performance.PerformanceTracker;
import com.sao.personnel.repository.PersonnelRepository;
import com.sao.personnel.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    @Autowired
    private PerformanceTracker tracker;

    @Override
    public List<PersonnelDto> getAllPersonnel() {
        // Veritabanından tüm personelleri çeker ve DTO'ya dönüştürür.
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

    /**
     * Geleneksel, sıralı (sequential) bir şekilde her personel için detayları çeker.
     * Bu metot bir baseline olarak kullanılabilir.
     */
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetails() {
        tracker.start();
        List<PersonnelDto> personnelList = getAllPersonnel();
        for (PersonnelDto personnelDto : personnelList) {
            // Her personel için I/O bağımlı çağrılar sırayla yapılır.
            List<EducationDto> educationDtoList = educationWebService.getEducationByPersonnelId(personnelDto.getId());
            personnelDto.setEducations(educationDtoList);

            List<TaskDto> taskDtoList = taskWebService.getTaskByPersonnelId(personnelDto.getId());
            personnelDto.setTasks(taskDtoList);
        }
        tracker.stop("Sequential Personnel Details Fetch");
        return personnelList;
    }

    /**
     * Sanal thread'ler (Virtual Threads) kullanarak personellerin detaylarını paralel olarak çeker.
     * Her bir personel için eğitim ve görev bilgileri de kendi içinde paralel olarak alınır.
     */
    @Transactional(readOnly = true)
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread() {
        tracker.start();
        List<PersonnelDto> personnelList = getAllPersonnel();

        // Java 21+ ile gelen sanal thread havuzu. Her görev için yeni bir sanal thread oluşturur.
        // Bu, I/O-bound işlemler için idealdir çünkü thread'ler bloklandığında işletim sistemi thread'ini meşgul etmez.
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            List<CompletableFuture<PersonnelDto>> futures = personnelList.stream()
                    .map(personnel -> CompletableFuture.supplyAsync(() -> {

                        // Her personel için iki web servisi çağrısını aynı anda başlatıyoruz.
                        CompletableFuture<Void> educationFuture = CompletableFuture.runAsync(() ->
                                personnel.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId())), executorService);

                        CompletableFuture<Void> taskFuture = CompletableFuture.runAsync(() ->
                                personnel.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId())), executorService);

                        // İki çağrı da tamamlanana kadar bekliyoruz.
                        CompletableFuture.allOf(educationFuture, taskFuture).join();

                        return personnel;
                    }, executorService))
                    .collect(Collectors.toList());

            // Tüm personeller için başlatılan asenkron işlemlerin tamamlanmasını bekleyip sonuçları topluyoruz.
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .join();
        } finally {
            tracker.stop("Virtual Thread Personnel Details Fetch");
        }
    }

    /**
     * Platform thread'leri (Platform Threads) kullanarak personellerin detaylarını paralel olarak çeker.
     * Karşılaştırma için kullanılır.
     */
    @Transactional(readOnly = true)
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsPlatformThread() {
        tracker.start();
        List<PersonnelDto> personnelList = getAllPersonnel();

        int poolSize = Runtime.getRuntime().availableProcessors() * 10;
        // Ana görevler için birincil thread havuzu
        ExecutorService mainExecutor = Executors.newFixedThreadPool(poolSize);
        // Alt görevler (web servisi çağrıları) için ayrı bir thread havuzu.
        // Bu ayrım, deadlock'u engeller.
        ExecutorService subTaskExecutor = Executors.newFixedThreadPool(poolSize);

        try {
            List<CompletableFuture<PersonnelDto>> futures = personnelList.stream()
                    .map(personnel -> CompletableFuture.supplyAsync(() -> {

                        // Alt görevleri, onlara özel olan 'subTaskExecutor' havuzunda çalıştırıyoruz.
                        CompletableFuture<Void> educationFuture = CompletableFuture.runAsync(() ->
                                personnel.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId())), subTaskExecutor);

                        CompletableFuture<Void> taskFuture = CompletableFuture.runAsync(() ->
                                personnel.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId())), subTaskExecutor);

                        // Ana görev thread'i, alt görevlerin bitmesini beklerken bloke olur.
                        // Alt görevler farklı bir havuzda çalıştığı için kaynak beklemez ve deadlock olmaz.
                        CompletableFuture.allOf(educationFuture, taskFuture).join();

                        return personnel;
                    }, mainExecutor)) // Ana görevi 'mainExecutor' havuzuna gönderiyoruz.
                    .collect(Collectors.toList());

            // Tüm ana görevlerin tamamlanmasını bekleyip sonuçları topluyoruz.
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .join();
        } finally {
            // İşimiz bittiğinde her iki havuzu da kapatmayı unutmuyoruz.
            mainExecutor.shutdown();
            subTaskExecutor.shutdown();
            tracker.stop("Platform Thread Personnel Details Fetch");
        }
    }

    @Override
    public PersonnelDto getPersonnelDetails(Long personnelId) {
        tracker.start();
        Personnel personnel = getPersonnelById(personnelId);
        if (personnel == null) {
            return null;
        }

        PersonnelDto personnelDto = new PersonnelDto();
        personnelDto.setId(personnel.getId());
        personnelDto.setName(personnel.getName());
        personnelDto.setSurname(personnel.getSurname());

        // Entegrasyon ile veriler alınıyor.
        List<EducationDto> educationDto = educationWebService.getEducationByPersonnelId(personnelId);
        List<TaskDto> taskDto = taskWebService.getTaskByPersonnelId(personnelId);

        personnelDto.setEducations(educationDto);
        personnelDto.setTasks(taskDto);

        tracker.stop("Single Personnel Details Fetch");
        return personnelDto;
    }
}
