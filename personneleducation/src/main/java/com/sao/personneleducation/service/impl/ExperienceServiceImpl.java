package com.sao.personneleducation.service.impl;

import com.sao.personneleducation.entity.Experience;
import com.sao.personneleducation.repository.ExperienceRepository;
import com.sao.personneleducation.service.IExperienceService;
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
public class ExperienceServiceImpl implements IExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public List<Experience> saveAllExperiences(List<Experience> experiences) {
        return experienceRepository.saveAll(experiences);
    }
}
