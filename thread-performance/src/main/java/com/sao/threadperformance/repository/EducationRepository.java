package com.sao.threadperformance.repository;

import com.sao.threadperformance.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 23 May 2025
 * <p>
 * @description:
 */
@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query("SELECT e FROM Education e LEFT JOIN FETCH e.experiences WHERE e.id IN :educationIds")
    List<Education> findEducationsWithExperiences(@Param("educationIds") List<Long> educationIds);
}
