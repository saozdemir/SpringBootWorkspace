package com.sao.config;

import com.sao.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 20 Oca 2025
 * <p>
 * @description: Bu sınıf bir konfigürasyon dosyası beanlerin tutulacağı sınıf. Sahte veritabanı oluşturmak için kullanılacak.
 */
@Configuration
public class AppConfig {

    @Bean //Spring context de bean oluşturulması için bunu ekledik.
    public List<Employee> employeeList(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L,"Emir", "ÖZDEMİR"));
        employeeList.add(new Employee(2L,"Ahmet", "ÖZDEMİR"));
        employeeList.add(new Employee(3L,"Esra", "ÖZDEMİR"));
        return employeeList;
    }
}
