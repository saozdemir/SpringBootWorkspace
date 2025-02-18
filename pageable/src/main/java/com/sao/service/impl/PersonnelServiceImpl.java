package com.sao.service.impl;

import com.sao.model.dto.DepartmentDto;
import com.sao.model.dto.PersonnelDto;
import com.sao.model.entity.Personnel;
import com.sao.repository.PersonnelRepository;
import com.sao.service.IPersonnelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 17 Şub 2025
 * <p>
 * @description:
 */
@Service
public class PersonnelServiceImpl implements IPersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public Page<Personnel> findAllPageable(Pageable pageable) {
        return personnelRepository.findAllPageable(pageable);
    }

    @Override
    public List<PersonnelDto> toDTOList(List<Personnel> personnelList) {
        List<PersonnelDto> personnelDtoList = new ArrayList<>();
        for (Personnel personnel : personnelList) {
            PersonnelDto personnelDto = new PersonnelDto();
            DepartmentDto departmentDto = new DepartmentDto();

            BeanUtils.copyProperties(personnel, personnelDto);
            BeanUtils.copyProperties(personnel.getDepartment(), departmentDto);
            personnelDto.setDepartment(departmentDto);
            personnelDtoList.add(personnelDto);
        }
        return personnelDtoList;
    }
}
