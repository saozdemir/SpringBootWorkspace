package com.sao.personneleducation.service.impl;

import com.github.javafaker.Faker;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Experience;
import com.sao.personneleducation.repository.EducationRepository;
import com.sao.personneleducation.repository.ExperienceRepository;
import com.sao.personneleducation.service.ITestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Service
public class TestDataServiceImpl implements ITestDataService {

    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public String generateLoadTestData() {
        /** Eski verileri temizle*/
        clearExistingData();

        /** Sahte veriler oluştur.*/
        Faker faker = new Faker(new Locale("tr"));
        List<Education> allEducations = new ArrayList<>();
        List<Experience> allExperiences = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            // Assign 50 or 100 random educations to each personnel
            int minEducation = 5;
            int maxEducation = 8;
            int educationCount = (int) (Math.random() * (maxEducation - minEducation + 1)) + minEducation;
            for (int j = 0; j < educationCount; j++) {
                Education education = new Education();
                //Assign 100 or 200 random experiences to each education
                int minExperience = 10;
                int maxExperience = 20;
                int experienceCount = (int) (Math.random() * (maxExperience - minExperience + 1)) + minExperience;
                List<Experience> experiences = new ArrayList<>();
                for (int k = 0; k < experienceCount; k++) {
                    Experience experience = new Experience();
                    experience.setName(faker.educator().course());
                    experience.setScore(1 + faker.number().numberBetween(0, 100)); // Random score between 1-100
                    experience.setEducation(education);
                    experiences.add(experience);
                    allExperiences.add(experience);
                }
                education.setName(faker.job().title());
                education.setExperiences(experiences);
                education.setPersonnelId((long) i);

//                educationRepository.save(education);
                allEducations.add(education);
            }
            educationRepository.saveAll(allEducations);
        }

        return "Create Load Test Data successfully: " + allEducations.size() + " Education, " +
                allExperiences.size() + " Experience records created.";
    }


    private void clearExistingData() {
        educationRepository.deleteAll();
        experienceRepository.deleteAll();
    }
}

