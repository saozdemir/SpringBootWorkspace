package com.sao.personnel.service.impl;

import com.github.javafaker.Faker;
import com.sao.personnel.entity.Personnel;
import com.sao.personnel.repository.PersonnelRepository;
import com.sao.personnel.service.ITestDataService;
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

    @Override
    public String generateLoadTestData() {
        /** Eski verileri temizle*/
        clearExistingData();

        /** Sahte veriler oluştur.*/
        Faker faker = new Faker(new Locale("tr"));

        /** Create Personnel*/

        List<Personnel> personnelList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Personnel personnel = new Personnel();
            personnel.setName(faker.name().firstName());
            personnel.setSurname(faker.name().lastName());
            personnelList.add(personnel);
        }
        personnelRepository.saveAll(personnelList);

        return "Create Load Test Data successfully: " +
                "1000 Personnel records created.";
    }


    private void clearExistingData() {
        personnelRepository.deleteAll();
    }
}

