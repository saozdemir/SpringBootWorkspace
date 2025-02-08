package com.sao.controller;

import com.sao.model.dto.EmployeeDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
public interface IEmployeeController {
    EmployeeDto findEmployeeById(@Valid @NotNull Long id);
}
