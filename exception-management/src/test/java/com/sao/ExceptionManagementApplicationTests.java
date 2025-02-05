package com.sao;

import com.sao.model.dto.EmployeeDto;
import com.sao.service.IEmployeeService;
import com.sao.starter.ExceptionManagementStarter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ExceptionManagementStarter.class})
class ExceptionManagementApplicationTests {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach çalıştı.");
    }

    @Test
    public void testFindEmployeeById(){
        EmployeeDto employeeDto = employeeService.findEmployeeById(1L);
        assertNotNull(employeeDto);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach çalıştı.");
    }

}
