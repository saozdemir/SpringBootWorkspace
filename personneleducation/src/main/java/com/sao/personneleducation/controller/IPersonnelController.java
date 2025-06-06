package com.sao.personneleducation.controller;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.dto.PersonnelDto;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Personnel;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
public interface IPersonnelController {

    Personnel savePersonnel(Personnel personnel);

    List<Personnel> getAllPersonnel();

    Personnel getPersonnelById(Long id);

    PersonnelDto getPersonnelEducations(Long personnelId);

    List<Personnel> searchPersonnel(String name, String surname);

    PersonnelDto getPersonnelDtoById(Long id);
}
