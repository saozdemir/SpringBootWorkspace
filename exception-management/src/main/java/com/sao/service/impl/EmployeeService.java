package com.sao.service.impl;

import com.sao.exception.BaseException;
import com.sao.exception.ErrorMessage;
import com.sao.exception.MessageType;
import com.sao.model.dto.DepartmentDto;
import com.sao.model.dto.EmployeeDto;
import com.sao.model.entity.Department;
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
 * @date 04 Şub 2025
 * <p>
 * @description:
 */
@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        EmployeeDto employeeDto = new EmployeeDto();
        DepartmentDto departmentDto = new DepartmentDto();

        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Employee employee = optional.get();
        Department department = employee.getDepartment();

        BeanUtils.copyProperties(employee, employeeDto);
        BeanUtils.copyProperties(department, departmentDto);

        employeeDto.setDepartment(departmentDto);

        return employeeDto;
    }
}
