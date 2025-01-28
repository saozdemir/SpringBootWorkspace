package com.sao.repository;

import com.sao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description:
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
