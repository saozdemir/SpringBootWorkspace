package com.sao.personneleducation.service.impl;

import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Personnel;
import com.sao.personneleducation.repository.PersonnelRepository;
import com.sao.personneleducation.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Personnel savePersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    @Override
    public Personnel getPersonnelById(Long id) {
        return personnelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Personnel> saveAllPersonnel(List<Personnel> personnelList) {
        return personnelRepository.saveAll(personnelList);
    }

    @Override
    public List<Education> getEducationsByPersonnelId(Long personnelId) {
        return personnelRepository.findEducationsByPersonnelId(personnelId);
    }

    @Override
    public List<Personnel> searchPersonnel(String name, String surname) {
        return personnelRepository.findByNameAndSurname(name, surname);
    }
}
