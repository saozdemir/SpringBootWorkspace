package com.sao.services;

import com.sao.dto.EmployeeDto;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 31 Oca 2025
 * <p>
 * @description:
 */
public interface IEmployeeService {

    List<EmployeeDto> findAllEmployees();
}
