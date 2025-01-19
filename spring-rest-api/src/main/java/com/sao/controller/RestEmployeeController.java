package com.sao.controller;

import com.sao.model.Employee;
import com.sao.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 20 Oca 2025
 * <p>
 * @description: Controller katmanı. Bunu belirtmek için @RestController anotasyonu kullandık. İsteğin ilk karşılanacağı alan.
 */
@RestController
@RequestMapping("/rest/api") //Kök url tanımlaması. Tüm metotların başına eklenmiş olur.
public class RestEmployeeController {

    @Autowired //Service katmanı ile bağladık. Spring Context'de oluşan service'i burada tanımladık.
    private EmployeeService employeeService;

    @GetMapping(path="/employee-list")
    public List<Employee> getAllEmployeeList() {
        return employeeService.getAllEmployeeList();
    }
}
