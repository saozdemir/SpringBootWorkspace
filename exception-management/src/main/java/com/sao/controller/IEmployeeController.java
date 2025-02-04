package com.sao.controller;

import com.sao.model.RootEntity;
import com.sao.model.dto.EmployeeDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description:
 */
public interface IEmployeeController {

    RootEntity<EmployeeDto> findEmployeeById(Long id);
}
