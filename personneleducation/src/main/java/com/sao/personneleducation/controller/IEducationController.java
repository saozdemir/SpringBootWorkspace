package com.sao.personneleducation.controller;

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

    Education saveEducation(Education education);

    List<Education> getAllEducations();
}
