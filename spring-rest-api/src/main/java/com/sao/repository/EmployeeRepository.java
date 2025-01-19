package com.sao.repository;

import com.sao.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
