package com.sao.controller;

import com.sao.model.Employee;
import com.sao.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 20 Oca 2025
 * <p>
 * @description: Controller katmanı. Bunu belirtmek için @RestController anotasyonu kullandık. İsteğin ilk karşılanacağı alan.
 */
@RestController
@RequestMapping("/rest/api/employee") //Kök url tanımlaması. Tüm metotların başına eklenmiş olur.
public class RestEmployeeController {

    @Autowired //Service katmanı ile bağladık. Spring Context'de oluşan service'i burada tanımladık.
    private EmployeeService employeeService;

    @GetMapping(path = "/list")
    public List<Employee> getAllEmployeeList() {
        return employeeService.getAllEmployeeList();
    }

    /**
     * @param id
     * @return
     * @PathVariable(name = "id", required = true): Gelecek olan alanın adı "id" ve zorunlu demek.
     * @GetMapping(path="/employee-list/{id}"): gelen id değerini {} parantezler içine alır.
     */
    @GetMapping(path = "/list/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id", required = true) Long id) {
        return employeeService.getEmployeeById(id);
    }

    /**
     * Kritere göre sorgu yapılmak istendiğinde @RequestParam kullanılır.
     * Kök url + path den sonra ? ile parametreler eklenmeye başlanır, her parametre arasına & işareti konulur.
     * http://localhost:8080/rest/api/employee/with-params?name=Emir&surname=ÖZDEMİR
     *
     * @param name
     * @param surname
     * @return
     * @RequestParam(name = "name", required = false)
     * @RequestParam(name = "surname", required = false)
     */
    @GetMapping(path = "/with-params")
    public List<Employee> getEmployeeWithParams(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "surname", required = false) String surname) {
        return employeeService.getEmployeeWithParams(name, surname);
    }

    /**
     * JSON Formatında yeni bir kayıt ekleme işlemi için @RequestBody kullanılır.
     * Kök url + path ile gelen POST isteğini işler.
     *
     * @param newEmployee
     * @return
     */
    @PostMapping(path = "/save-employee")
    public Employee saveEmployee(@RequestBody Employee newEmployee) {
        return employeeService.saveEmployee(newEmployee);
    }

    /**
     * Kök url +path den sonra path değişkenine ilave olarak {} parantezi arasına değer yazılır.
     * Kök url +path den sonra silinecek id belirtmek için @PathVariable(name="id") kullanıldı.
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/delete-employee/{id}")
    public boolean deleteEmployee(@PathVariable(name = "id") Long id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * Kök url +path den sonra path değişkenine ilave olarak {} parantezi arasına değer yazılır.
     * Kök url +path den sonra güncellenecek id belirtmek için @PathVariable(name="id") kullanıldı.
     *
     * @param id
     * @param updateEmployee
     * @return
     * @RequestBody ile de güncellenecek içeriğin JSON formatında gelen tanımlaması yapıdı.
     */
    @PutMapping(path = "/update-employee/{id}")
    public Employee updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee updateEmployee) {
        return employeeService.updateEmployee(id, updateEmployee);
    }
}
