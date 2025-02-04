package com.sao.service;

import com.sao.model.dto.EmployeeDto;
import com.sao.model.entity.Employee;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description:
 */
public interface IEmployeeService {

    EmployeeDto findEmployeeById(Long id);
}
