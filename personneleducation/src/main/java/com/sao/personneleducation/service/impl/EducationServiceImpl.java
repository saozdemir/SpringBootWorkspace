package com.sao.personneleducation.service.impl;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.dto.ExperienceDto;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Experience;
import com.sao.personneleducation.repository.EducationRepository;
import com.sao.personneleducation.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<EducationDto> getAllEducations() {
        return List.of();
    }

    @Override
    public List<Education> saveAllEducations(List<Education> educations) {
        return educationRepository.saveAll(educations);
    }

    @Override
    public List<EducationDto> getEducationsByPersonnelId(Long personnelId) {
        List<Education> educations = educationRepository.getEducationByPersonnelId(personnelId);
        List<EducationDto> educationDtoList = new ArrayList<>();
        for (Education education : educations) {
            List<Experience> experiences = education.getExperiences();
            List<ExperienceDto> experienceDtoList = new ArrayList<>();
            for (Experience experience : experiences) {
                ExperienceDto experienceDto = new ExperienceDto();
                experienceDto.setId(experience.getId());
                experienceDto.setName(experience.getName());
                experienceDto.setScore(experience.getScore());
                experienceDtoList.add(experienceDto);
            }
            EducationDto educationDto = new EducationDto();
            educationDto.setId(education.getId());
            educationDto.setName(education.getName());
            educationDto.setExperiences(experienceDtoList);
            educationDtoList.add(educationDto);
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return educationDtoList;
    }
}
