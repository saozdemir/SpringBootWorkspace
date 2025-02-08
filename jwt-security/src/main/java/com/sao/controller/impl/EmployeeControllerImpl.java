package com.sao.controller.impl;

import com.sao.controller.IEmployeeController;
import com.sao.model.dto.EmployeeDto;
import com.sao.service.IEmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements IEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(path = "/{id}")
    @Override
    public EmployeeDto findEmployeeById(@Valid @NotNull @PathVariable(value = "id") Long id) {
        return employeeService.findEmployeeById(id);
    }
}
