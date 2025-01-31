package com.sao.services.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.sao.dto.DepartmentDto;
import com.sao.dto.EmployeeDto;
import com.sao.entities.Employee;
import com.sao.repository.EmployeeRepository;
import com.sao.services.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 31 Oca 2025
 * <p>
 * @description:
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        if (employeeList != null && !employeeList.isEmpty()) {
            for (Employee employee : employeeList) {
                EmployeeDto employeeDto = new EmployeeDto();
                BeanUtils.copyProperties(employee, employeeDto);

                employeeDto.setDepartment(new DepartmentDto(employee.getDepartment().getId(),
                        employee.getDepartment().getDepartmentName()));

                employeeDtoList.add(employeeDto);
            }
        }


        return employeeDtoList;
    }
}
