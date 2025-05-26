package com.sao.personneleducation.repository;

import com.sao.personneleducation.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    // Additional query methods can be defined here if needed
}
