package com.sao.service.impl;

import com.sao.model.entity.Personnel;
import com.sao.repository.PersonnelRepository;
import com.sao.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
