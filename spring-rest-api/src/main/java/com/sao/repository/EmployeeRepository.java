package com.sao.repository;

import com.sao.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 20 Oca 2025
 * <p>
 * @description: Repository katman. Bunu belirtmek için @Repository anotasyonunu kullandık. Veritabanı işlemlerinin yapılacağı katman
 */
@Repository
public class EmployeeRepository {

    @Autowired //AppConfig içinde tanımlanan bean enjekte edildi. sahte veri üretip veritabanından çekiyormuşuz gibi.
    private List<Employee> employeeList;

    public List<Employee> getAllEmployeList() {
        return employeeList;
    }

    public Employee getEmployeeById(Long id) {
        Employee findEmployee = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                findEmployee = employee;
                break;
            }
        }
        return findEmployee;
    }

    public List<Employee> getEmployeeWithParams(String name, String surname) {
        List<Employee> employeeWithParams = new ArrayList<>();
        if (name == null && surname == null) {
            return employeeList;
        }

        for (Employee employee : employeeList) {
            if (name != null && surname != null) {
                if (employee.getName().equalsIgnoreCase(name)
                        && employee.getSurname().equalsIgnoreCase(surname)) {
                    employeeWithParams.add(employee);
                }
            }

            if (name != null && surname == null) {
                if (employee.getName().equalsIgnoreCase(name)) {
                    employeeWithParams.add(employee);
                }
            }

            if (surname != null && name == null) {
                if (employee.getSurname().equalsIgnoreCase(surname)) {
                    employeeWithParams.add(employee);
                }
            }
        }
        return employeeWithParams;
    }

    public Employee saveEmployee(Employee newEmployee) {
        employeeList.add(newEmployee);
        return newEmployee;
    }

    public boolean deleteEmployee(Long id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeList.remove(employee);
                return true;
            }
        }
        return false;
    }

    public Employee updateEmployee(Long id, Employee updateEmployee) {
        Employee updatedEmployee = null;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                employeeList.set(i, updateEmployee);
                updatedEmployee = updateEmployee;
            }
        }
        return updatedEmployee;
    }
}
