package com.sao.personneleducation.service;

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

    Education saveEducation(Education education);

    List<Education> getAllEducations();

    List<Education> saveAllEducations(List<Education> educations);
}
