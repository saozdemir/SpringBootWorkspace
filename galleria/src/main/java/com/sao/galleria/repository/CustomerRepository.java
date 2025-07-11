package com.sao.galleria.repository;

import com.sao.galleria.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
