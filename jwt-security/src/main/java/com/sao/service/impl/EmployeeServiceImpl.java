package com.sao.service.impl;

import com.sao.model.dto.DepartmentDto;
import com.sao.model.dto.EmployeeDto;
import com.sao.model.entity.Employee;
import com.sao.repository.EmployeeRepository;
import com.sao.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        EmployeeDto employeeDto = new EmployeeDto();
        DepartmentDto departmentDto = new DepartmentDto();
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            BeanUtils.copyProperties(optional.get(), employeeDto);
            BeanUtils.copyProperties(optional.get().getDepartment(), departmentDto);
            employeeDto.setDepartment(departmentDto);
        }
        return employeeDto;
    }
}
