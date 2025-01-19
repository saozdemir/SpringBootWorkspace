package com.sao.services;

import com.sao.model.Employee;
import com.sao.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 20 Oca 2025
 * <p>
 * @description: Service katmanı.Bunu belirtmek için @Service anotasyonu kullandık. İsteğin lojik kontrollerinin yapılacağı alan.
 */
@Service
public class EmployeeService {

    @Autowired // Repository katmanı ile bağladık.Spring Context'de oluşan repository'yi buraya enjekte ettik.
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeeList() {
        // Logic ve null kontroller burada yapılacak.
        return employeeRepository.getAllEmployeList();
    }
}
