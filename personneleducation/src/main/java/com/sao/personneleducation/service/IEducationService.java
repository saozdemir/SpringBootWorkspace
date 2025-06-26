package com.sao.personneleducation.service;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.entity.Education;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
public interface IEducationService {

    List<EducationDto> getAllEducations();

    List<Education> saveAllEducations(List<Education> educations);

    List<EducationDto> getEducationsByPersonnelId(Long personnelId);
}
