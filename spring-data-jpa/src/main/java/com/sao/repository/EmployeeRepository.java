package com.sao.repository;

import com.sao.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 31 Oca 2025
 * <p>
 * @description:
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
