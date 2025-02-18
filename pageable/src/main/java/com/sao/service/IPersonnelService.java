package com.sao.service;

import com.sao.model.dto.PersonnelDto;
import com.sao.model.entity.Personnel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 17 Şub 2025
 * <p>
 * @description:
 */
public interface IPersonnelService {
    Page<Personnel> findAllPageable(Pageable pageable);

    List<PersonnelDto> toDTOList(List<Personnel> personnelList);
}
