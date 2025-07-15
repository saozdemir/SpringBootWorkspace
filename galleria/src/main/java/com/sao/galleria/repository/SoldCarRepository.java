package com.sao.galleria.repository;

import com.sao.galleria.model.SoldCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
@Repository
public interface SoldCarRepository extends JpaRepository<SoldCar, Long> {
}
