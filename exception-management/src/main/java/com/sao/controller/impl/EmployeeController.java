package com.sao.controller.impl;

import com.sao.controller.IEmployeeController;
import com.sao.model.RootEntity;
import com.sao.model.dto.EmployeeDto;
import com.sao.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/rest/api/employee")
public class EmployeeController extends RestBaseController implements IEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(path = "/list/{id}")
    @Override
    public RootEntity<EmployeeDto> findEmployeeById(@PathVariable(value = "id") Long id) {
        return ok(employeeService.findEmployeeById(id));
    }
}
