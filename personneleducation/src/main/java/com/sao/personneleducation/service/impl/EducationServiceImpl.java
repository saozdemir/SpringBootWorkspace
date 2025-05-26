package com.sao.personneleducation.service.impl;

import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.repository.EducationRepository;
import com.sao.personneleducation.service.IEducationService;
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
public class EducationServiceImpl implements IEducationService {
    @Autowired
    private EducationRepository educationRepository;

    @Override
    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public List<Education> getAllEducations() {
        return educationRepository.findAll();
    }

    @Override
    public List<Education> saveAllEducations(List<Education> educations) {
        return educationRepository.saveAll(educations);
    }
}
