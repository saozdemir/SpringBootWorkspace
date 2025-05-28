package com.sao.personneleducation.service;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.dto.PersonnelDto;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Personnel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
public interface IPersonnelService {

    Personnel savePersonnel(Personnel personnel);

    List<Personnel> getAllPersonnel();

    Personnel getPersonnelById(Long id);

    List<Personnel> saveAllPersonnel(List<Personnel> personnelList);

    PersonnelDto getEducationsByPersonnelId(Long personnelId);

    List<Personnel> searchPersonnel(String name, String surname);

    PersonnelDto getPersonnelDtoById(Long personnelId);
}
