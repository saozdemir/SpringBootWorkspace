package com.sao.personnel.service;

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
public interface IPersonnelService {

    List<PersonnelDto> getAllPersonnel();

    Personnel getPersonnelById(Long id);

    List<PersonnelDto> getPersonnelListWithAllDetails();

    List<PersonnelDto>  getPersonnelListWithAllDetailsVirtualThread();

    List<PersonnelDto> getPersonnelListWithAllDetailsPlatformThread();

    List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThreadWithSemaphore();

    PersonnelDto getPersonnelDetails(Long personnelId);
}
