package com.sao.personneleducation.service;

import com.sao.personneleducation.entity.Experience;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
public interface IExperienceService {

    Experience saveExperience(Experience experience);

    List<Experience> getAllExperiences();

    List<Experience> saveAllExperiences(List<Experience> experiences);
}
