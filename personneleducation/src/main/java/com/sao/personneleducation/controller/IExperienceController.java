package com.sao.personneleducation.controller;

import com.sao.personneleducation.entity.Experience;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
public interface IExperienceController {

    Experience saveExperience(Experience experience);

    List<Experience> getAllExperiences();
}
