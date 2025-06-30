package com.sao.personnel.controller;

import com.sao.personnel.dto.PersonnelDto;
import com.sao.personnel.entity.Personnel;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
public interface IPersonnelController {


    List<PersonnelDto> getAllPersonnel();

    Personnel getPersonnelById(Long id);

    PersonnelDto getPersonnelEducations(Long personnelId);

    PersonnelDto getPersonnelDetails(Long personnelId);

    List<PersonnelDto> getPersonnelListWithAllDetails();

    List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread();

    List<PersonnelDto> getPersonnelListWithAllDetailsPlatformThread();
}
