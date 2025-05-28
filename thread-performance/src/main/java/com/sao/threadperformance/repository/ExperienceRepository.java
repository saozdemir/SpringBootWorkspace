package com.sao.threadperformance.repository;

import com.sao.threadperformance.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 23 May 2025
 * <p>
 * @description:
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
