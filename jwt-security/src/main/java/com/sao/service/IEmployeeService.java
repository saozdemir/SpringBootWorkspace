package com.sao.service;

import com.sao.model.dto.EmployeeDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
public interface IEmployeeService {
    EmployeeDto findEmployeeById(Long id);
}
