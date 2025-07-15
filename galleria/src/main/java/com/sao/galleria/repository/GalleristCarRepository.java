package com.sao.galleria.repository;

import com.sao.galleria.model.GalleristCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar, Long> {

}
