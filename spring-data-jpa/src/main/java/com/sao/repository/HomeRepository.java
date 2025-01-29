package com.sao.repository;

import com.sao.entities.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Oca 2025
 * <p>
 * @description:
 */
@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

}
