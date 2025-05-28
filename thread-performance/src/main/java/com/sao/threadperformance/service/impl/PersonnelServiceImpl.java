package com.sao.threadperformance.service.impl;

import com.sao.threadperformance.entity.Education;
import com.sao.threadperformance.entity.Experience;
import com.sao.threadperformance.entity.Personnel;
import com.sao.threadperformance.repository.EducationRepository;
import com.sao.threadperformance.repository.ExperienceRepository;
import com.sao.threadperformance.repository.PersonnelRepository;
import com.sao.threadperformance.service.IPersonnelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 23 May 2025
 * <p>
 * @description:
 */
@Service
public class PersonnelServiceImpl implements IPersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    private final ExecutorService platformExecutor = Executors.newFixedThreadPool(50);
    private final ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @Transactional
    @Override
    public void createTestData() {
        // 100 adet Education oluştur
        Set<Education> educationList = new LinkedHashSet<>();
        for (int i = 1; i <= 100; i++) {
            Education education = new Education();
            education.setName("Education " + i);

            Set<Experience> experiences = new LinkedHashSet<>();
            for (int j = 1; j <= 500; j++) {
                Experience experience = new Experience();
                experience.setName("Experience " + j + " of Education " + i);
                experience.setScore((int) (Math.random() * 100));
                experience.setEducation(education);
                experiences.add(experience);
            }
            education.setExperiences(experiences);
            educationList.add(education);
        }
        educationRepository.saveAll(educationList);

        // 1000 adet Personnel oluştur ve rastgele Education atamaları yap
        Set<Personnel> personnelList = new LinkedHashSet<>();
        for (int i = 1; i <= 1000; i++) {
            Personnel personnel = new Personnel();
            personnel.setName("Name " + i);
            personnel.setSurname("Surname " + i);

            // Her personele rastgele 50 education ata
            Set<Education> assignedEducations = new LinkedHashSet<>();
            for (int j = 0; j < 50; j++) {
                Education randomEducation = educationList.stream().toList().get((int) (Math.random() * educationList.size()));
                assignedEducations.add(randomEducation);
                randomEducation.getPersonnelList().add(personnel);
            }

            personnel.setEducations((Set<Education>) assignedEducations);
            personnelList.add(personnel);
        }

        personnelRepository.saveAll(personnelList);
    }

    public List<Personnel> getAllPersonnel() throws InterruptedException, ExecutionException {
        Instant start = Instant.now();

        List<Long> personnelIds = personnelRepository.findTop1000ByOrderByIdAsc()
                .stream()
                .map(Personnel::getId)
                .collect(Collectors.toList());

        List<Callable<Personnel>> tasks = new ArrayList<>();

        for (Long id : personnelIds) {
            tasks.add(() -> {
                Personnel personnel = personnelRepository.findByIdWithEducations(id);
                List<Long> educationIds = personnel.getEducations()
                        .stream()
                        .map(Education::getId)
                        .collect(Collectors.toList());
                educationRepository.findEducationsWithExperiences(educationIds);
                return personnel;
            });
        }

        List<Future<Personnel>> futures = virtualExecutor.invokeAll(tasks);
        List<Personnel> personnelList = new ArrayList<>();
        for (Future<Personnel> f : futures) {
            personnelList.add(f.get());
        }

        Duration duration = Duration.between(start, Instant.now());
        System.out.println("IO-bound: Completed fetching 1000 personnel with virtual threads in " + duration.toMillis() + " ms.");
        return personnelList;
    }
}
