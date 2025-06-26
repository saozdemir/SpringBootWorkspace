package com.sao.personnel.service.impl;

import com.sao.personnel.dto.EducationDto;
import com.sao.personnel.dto.PersonnelDto;
import com.sao.personnel.dto.TaskDto;
import com.sao.personnel.entity.Personnel;
import com.sao.personnel.integration.IEducationWebService;
import com.sao.personnel.integration.ITaskWebService;
import com.sao.personnel.performance.ResourceTracker;
import com.sao.personnel.performance.ResourceTracker1;
import com.sao.personnel.performance.ResourceTracker2;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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


    @Override
    public List<PersonnelDto> getAllPersonnel() {
        List<Personnel> personnelList = personnelRepository.findAll();
        List<PersonnelDto> personnelDtoList = new ArrayList<>();
        for (Personnel personnel : personnelList) {
            PersonnelDto personnelDto = new PersonnelDto();
            personnelDto.setId(personnel.getId());
            personnelDto.setName(personnel.getName());
            personnelDto.setSurname(personnel.getSurname());
            personnelDtoList.add(personnelDto);
        }
        return personnelDtoList;
    }

    @Override
    public Personnel getPersonnelById(Long id) {
        return personnelRepository.findById(id).orElse(null);
    }

    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetails() {
        //Todo: Öncelikle burada 1000 personel için sorgulama yapılacak şekilde altyapı kurulacak.
        List<PersonnelDto> personnelList = getAllPersonnel();
        for (PersonnelDto personnelDto : personnelList) {
            // Entegrasyon ile eğitimleri al
            List<EducationDto> educationDtoList = educationWebService.getEducationByPersonnelId(personnelDto.getId());
            personnelDto.setEducations(educationDtoList);

            // Entegrasyon ile görevleri al
            List<TaskDto> taskDtoList = taskWebService.getTaskByPersonnelId(personnelDto.getId());
            personnelDto.setTasks(taskDtoList);
        }

        return personnelList;
    }

    @Transactional
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread() {
        // Todo: burada entegrasyon ile alınan verilerde zaman kaybını önlemek için virtual thread kullanılarak
        List<PersonnelDto> personnelList = getAllPersonnel();
        List<PersonnelDto> personnelDtoList = new ArrayList<>();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        try {
            List<Future<PersonnelDto>> futures = new ArrayList<>();

            for (PersonnelDto personnel : personnelList) {
                futures.add(executorService.submit(() -> {

//                    // Eğitimleri al
//                    List<EducationDto> educationDtoList = educationWebService.getEducationByPersonnelId(personnel.getId());
//
//                    // Görevleri al
//                    List<TaskDto> tasks = taskWebService.getTaskByPersonnelId(personnel.getId());
                    // Aynı personel için servis çağrılarını paralel başlat

                    // Web servis çağrılarını ayrı thread'lerde başlatacak böylece hız artışı sağlamış olacak.
                    Future<List<EducationDto>> educationFuture = executorService.submit(
                            () -> educationWebService.getEducationByPersonnelId(personnel.getId())
                    );
                    Future<List<TaskDto>> taskFuture = executorService.submit(
                            () -> taskWebService.getTaskByPersonnelId(personnel.getId())
                    );

                    // Sonuçları al
                    List<EducationDto> educations = educationFuture.get();
                    List<TaskDto> tasks = taskFuture.get();

                    personnel.setEducations(educations);
                    personnel.setTasks(tasks);

                    return personnel;
                }));
            }

            for (Future<PersonnelDto> future : futures) {
                personnelDtoList.add(future.get()); // Sonuçları topla
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.close(); // Kaynak sızıntısını önlemek için kapatılıyor
        }
        return personnelDtoList;
    }

    @Transactional
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsPlatformThread() {
        List<PersonnelDto> personnelList = getAllPersonnel();
        List<PersonnelDto> personnelDtoList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(50); // platform thread havuzu

        try {
            List<Future<PersonnelDto>> futures = new ArrayList<>();

            for (PersonnelDto personnel : personnelList) {
                futures.add(executorService.submit(() -> {
                    Future<List<EducationDto>> educationFuture = executorService.submit(
                            () -> educationWebService.getEducationByPersonnelId(personnel.getId())
                    );
                    Future<List<TaskDto>> taskFuture = executorService.submit(
                            () -> taskWebService.getTaskByPersonnelId(personnel.getId())
                    );

                    List<EducationDto> educations = educationFuture.get();
                    List<TaskDto> tasks = taskFuture.get();

                    personnel.setEducations(educations);
                    personnel.setTasks(tasks);

                    return personnel;
                }));
            }

            for (Future<PersonnelDto> future : futures) {
                personnelDtoList.add(future.get());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        return personnelDtoList;
    }


    @Override
    public PersonnelDto getPersonnelDetails(Long personnelId) {
        ResourceTracker tracker = new ResourceTracker();
        ResourceTracker1 tracker1 = new ResourceTracker1();
        ResourceTracker2 tracker2 = new ResourceTracker2();
        // Metrik ölçümüne başlanıyor
        tracker.start();
        tracker1.start();
        tracker2.start();

        PersonnelDto personnelDto = new PersonnelDto();
        Personnel personnel = getPersonnelById(personnelId);
        /** Entegrasyon ile veriler alınıyor.*/
        List<EducationDto> educationDto = educationWebService.getEducationByPersonnelId(personnelId);
        List<TaskDto> taskDto = taskWebService.getTaskByPersonnelId(personnelId);
        if (personnel != null) {
            personnelDto.setId(personnel.getId());
            personnelDto.setName(personnel.getName());
            personnelDto.setSurname(personnel.getSurname());
            personnelDto.setEducations(educationDto);
            personnelDto.setTasks(taskDto);
        }
        // Metrik ölçümü bitiriliyor
        tracker.stop("Single Personnel Details Fetch");
        tracker1.stop("Single Personnel Details Fetch1");
        tracker2.stop("Single Personnel Details Fetch2");
        return personnelDto;
    }


//    @Transactional
//    @Override
//    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread() {
//        List<Personnel> personnelList = getAllPersonnel();
//        List<PersonnelDto> personnelDtoList = new ArrayList<>();
//
//        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
//        try {
//            List<Future<PersonnelDto>> futures = new ArrayList<>();
//
//            for (Personnel personnel : personnelList) {
//                futures.add(executorService.submit(() -> {
//                    PersonnelDto personnelDto = new PersonnelDto();
//                    BeanUtils.copyProperties(personnel, personnelDto);
//
//                    // Eğitimleri al
//                    List<Education> educations = personnelRepository.findEducationsByPersonnelId(personnel.getId());
//                    List<EducationDto> educationDtoList = new ArrayList<>();
//
//                    for (Education education : educations) {
//                        EducationDto educationDto = new EducationDto();
//                        BeanUtils.copyProperties(education, educationDto);
//
//                        // Lazy koleksiyon: Experience -> DTO'ya mapleniyor
//                        List<ExperienceDto> experienceDtoList = new ArrayList<>();
//                        for (Experience experience : education.getExperiences()) {
//                            ExperienceDto experienceDto = new ExperienceDto();
//                            BeanUtils.copyProperties(experience, experienceDto);
//                            experienceDtoList.add(experienceDto);
//                        }
//                        educationDto.setExperiences(experienceDtoList);
//
//                        educationDtoList.add(educationDto);
//                    }
//
//                    personnelDto.setEducations(educationDtoList);
//
//                    // Görevleri al
//                    List<TaskDto> tasks = taskWebService.getTaskByPersonnelId(personnel.getId());
//                    personnelDto.setTasks(tasks);
//
//                    return personnelDto;
//                }));
//            }
//
//            for (Future<PersonnelDto> future : futures) {
//                personnelDtoList.add(future.get()); // Sonuçları topla
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error processing personnel details", e);
//        } finally {
//            executorService.close();
//        }
//
//        return personnelDtoList;
//    }

}
