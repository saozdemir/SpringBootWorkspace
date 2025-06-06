package com.sao.personneleducation.service.impl;

import com.github.javafaker.Faker;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Experience;
import com.sao.personneleducation.entity.Personnel;
import com.sao.personneleducation.repository.EducationRepository;
import com.sao.personneleducation.repository.ExperienceRepository;
import com.sao.personneleducation.repository.PersonnelRepository;
import com.sao.personneleducation.service.ITestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PersonnelRepository personnelRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @Transactional
    @Override
    public String generateTestData() {
        /** Eski verileri temizle*/
        clearExistingData();

        /** Create Educations*/
        List<Education> educations = new ArrayList<>();
        String[] educationNames = {"Computer Science", "Electrical Engineering", "Business Administration",
                "Mechanical Engineering", "Industrial Design"};
        for (String name : educationNames) {
            Education education = new Education();
            education.setName(name);
            educations.add(education);
        }
        educationRepository.saveAll(educations);

        /**Create Experiences for each Education*/
        Random random = new Random();
        List<Experience> allExperiences = new ArrayList<>();
        String[] experienceNames = {"Internship", "Junior Developer", "Senior Developer",
                "Project Manager", "Team Lead", "Architect", "Consultant"};

        for (Education education : educations) {
            int expCount = 5; // Each education will have 5 experiences
            for (int i = 0; i < expCount; i++) {
                String expName = experienceNames[random.nextInt(experienceNames.length)];
                int score = 1 + random.nextInt(10); // Random score between 1-10
                Experience experience = new Experience();
                experience.setName(expName);
                experience.setScore(score);
                experience.setEducation(education);
                allExperiences.add(experience);
            }
        }
        experienceRepository.saveAll(allExperiences);


        /** Create Personnel and assign random educations*/
        String[] firstNames = {"Ahmet", "Mehmet", "Ayşe", "Fatma", "Mustafa",
                "Emine", "Ali", "Zeynep", "Hüseyin", "Elif"};
        String[] lastNames = {"Özdemir", "Kaya", "Demir", "Çelik", "Şahin",
                "Arslan", "Koç", "Yıldız", "Aksoy", "Öztürk"};

        List<Personnel> personnelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Personnel personnel = new Personnel();
            personnel.setName(firstNames[i]);
            personnel.setSurname(lastNames[i]);

            // Assign 2-3 random educations to each personnel
            int educationCount = 2 + random.nextInt(2); // 2 or 3
            List<Education> assignedEducations = new ArrayList<>();
            for (int j = 0; j < educationCount; j++) {
                Education randomEdu = educations.get(random.nextInt(educations.size()));
                if (!assignedEducations.contains(randomEdu)) {
                    assignedEducations.add(randomEdu);
                }
            }
            personnel.setEducations(assignedEducations);
            personnelList.add(personnel);
        }
        personnelRepository.saveAll(personnelList);

        return "Test data generated successfully: " +
                "10 Personnel, " + educations.size() + " Education, " +
                allExperiences.size() + " Experience records created.";
    }

    @Override
    public String generateLoadTestData() {
        /** Eski verileri temizle*/
        clearExistingData();

        /** Sahte veriler oluştur.*/
        Faker faker = new Faker(new Locale("tr"));

        /** Create Educations: 200 tane eğitim programı oluştur.*/
        List<Education> educations = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Education education = new Education();
            education.setName(faker.job().title());
            educations.add(education);
        }
        educationRepository.saveAll(educations);

        /**Create Experiences for each Education*/
        Random random = new Random();
        List<Experience> allExperiences = new ArrayList<>();
        for (Education education : educations) {
            int minExpCount = 90;
            int maxExpCount = 100;
            int expCount = (int) (Math.random() * (maxExpCount - minExpCount + 1)) + minExpCount; // Each education will have 90 or 100 experiences
            for (int i = 0; i < expCount; i++) {
                int score = 1 + random.nextInt(100); // Random score between 1-100
                Experience experience = new Experience();
                experience.setName(faker.educator().course());
                experience.setScore(score);
                experience.setEducation(education);
                allExperiences.add(experience);
            }
        }
        experienceRepository.saveAll(allExperiences);

        /** Create Personnel and assign random educations*/

        List<Personnel> personnelList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Personnel personnel = new Personnel();
            personnel.setName(faker.name().firstName());
            personnel.setSurname(faker.name().lastName());

            // Assign 40 or 50 random educations to each personnel
            int minEduCount = 40;
            int maxEduCount = 50;
            int educationCount = (int) (Math.random() * (maxEduCount - minEduCount + 1)) + minEduCount;
            List<Education> assignedEducations = new ArrayList<>();
            for (int j = 0; j < educationCount; j++) {
                Education randomEdu = educations.get(random.nextInt(educations.size()));
                if (!assignedEducations.contains(randomEdu)) {
                    assignedEducations.add(randomEdu);
                }
            }
            personnel.setEducations(assignedEducations);
            personnelList.add(personnel);
        }
        personnelRepository.saveAll(personnelList);

        return "Create Load Test Data successfully: " +
                "1000 Personnel, " + educations.size() + " Education, " +
                allExperiences.size() + " Experience records created.";
    }


    private void clearExistingData() {
        personnelRepository.deleteAll();
        educationRepository.deleteAll();
        experienceRepository.deleteAll();
    }
}

