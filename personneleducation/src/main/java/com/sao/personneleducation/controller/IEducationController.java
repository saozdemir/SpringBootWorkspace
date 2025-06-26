package com.sao.personneleducation.controller;

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
public interface IEducationController {

    List<EducationDto> getAllEducations();

    List<EducationDto> getEducationsByPersonnelId(Long personnelId);
}
