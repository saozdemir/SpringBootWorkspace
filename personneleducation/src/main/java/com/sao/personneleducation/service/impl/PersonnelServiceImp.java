package com.sao.personneleducation.service.impl;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.dto.PersonnelDto;
import com.sao.personneleducation.dto.TaskDto;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Personnel;
import com.sao.personneleducation.integration.ITaskWebService;
import com.sao.personneleducation.repository.PersonnelRepository;
import com.sao.personneleducation.service.IPersonnelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Service
public class PersonnelServiceImp implements IPersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private ITaskWebService taskWebService;

    @Override
    public Personnel savePersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    @Override
    public Personnel getPersonnelById(Long id) {
        return personnelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Personnel> saveAllPersonnel(List<Personnel> personnelList) {
        return personnelRepository.saveAll(personnelList);
    }

    @Override
    public PersonnelDto getEducationsByPersonnelId(Long personnelId) {
        Personnel personnel = personnelRepository.findById(personnelId).orElse(null);
        PersonnelDto personnelDto = new PersonnelDto();
        if (personnel != null) {
            List<Education> educations = personnelRepository.findEducationsByPersonnelId(personnelId);
            List<EducationDto> educationDtoList = new ArrayList<>();
            BeanUtils.copyProperties(personnel, personnelDto);
            for (Education education : educations) {
                EducationDto educationDto = new EducationDto();
                BeanUtils.copyProperties(education, educationDto);
                educationDtoList.add(educationDto);
            }
            personnelDto.setEducations(educationDtoList);
        }
        return personnelDto;
    }

    @Override
    public List<Personnel> searchPersonnel(String name, String surname) {
        return personnelRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public PersonnelDto getPersonnelDtoById(Long personnelId) {
        Personnel personnel = getPersonnelById(personnelId);
        PersonnelDto personnelDto = new PersonnelDto();
        if (personnel != null) {
            List<TaskDto> tasks = taskWebService.getTaskByPersonnelId(personnelId);
            BeanUtils.copyProperties(personnel, personnelDto);
            personnelDto.setTasks(tasks);
        }

        return personnelDto;
    }

    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetails() {
        List<Personnel> personnelList = getAllPersonnel();
        List<PersonnelDto> personnelDtoList = new ArrayList<>();

        for (Personnel personnel : personnelList) {
            PersonnelDto personnelDto = new PersonnelDto();
            BeanUtils.copyProperties(personnel, personnelDto);


            // Get educations
            List<Education> educations = personnelRepository.findEducationsByPersonnelId(personnel.getId());
            List<EducationDto> educationDtoList = new ArrayList<>();
            for (Education education : educations) {
                EducationDto educationDto = new EducationDto();
                BeanUtils.copyProperties(education, educationDto);
                educationDtoList.add(educationDto);
            }
            personnelDto.setEducations(educationDtoList);

            // Get tasks
            List<TaskDto> tasks = taskWebService.getTaskByPersonnelId(personnel.getId());
            personnelDto.setTasks(tasks);

            personnelDtoList.add(personnelDto);
        }

        return personnelDtoList;
    }

    @Transactional
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread() {
        List<Personnel> personnelList = getAllPersonnel();
        List<PersonnelDto> personnelDtoList = new ArrayList<>();

        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        try {
            List<Future<PersonnelDto>> futures = new ArrayList<>();

            for (Personnel personnel : personnelList) {
                futures.add(executorService.submit(() -> {
                    PersonnelDto personnelDto = new PersonnelDto();
                    BeanUtils.copyProperties(personnel, personnelDto);

                    // Get educations
                    List<Education> educations = personnelRepository.findEducationsByPersonnelId(personnel.getId());
                    List<EducationDto> educationDtoList = new ArrayList<>();
                    for (Education education : educations) {
                        EducationDto educationDto = new EducationDto();
                        BeanUtils.copyProperties(education, educationDto);
                        educationDtoList.add(educationDto);
                    }
                    personnelDto.setEducations(educationDtoList);

                    // Get tasks
                    List<TaskDto> tasks = taskWebService.getTaskByPersonnelId(personnel.getId());
                    personnelDto.setTasks(tasks);

                    return personnelDto;
                }));
            }

            for (Future<PersonnelDto> future : futures) {
                personnelDtoList.add(future.get()); // Collect results
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing personnel details", e);
        } finally {
            executorService.close();
        }

        return personnelDtoList;
    }
}
