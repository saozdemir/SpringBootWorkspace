package com.sao.threadperformance.repository;

import com.sao.threadperformance.entity.Education;
import com.sao.threadperformance.entity.Personnel;
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
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    @Query("SELECT p FROM Personnel p " +
            "LEFT JOIN FETCH p.educations e " +
            "LEFT JOIN FETCH e.experiences " +
            "WHERE p.id = :id")
    Personnel findByIdWithEducationsAndExperiences(@Param("id") Long id);

    List<Personnel> findTop1000ByOrderByIdAsc(); // ID’leri almak için

    @Query("SELECT p FROM Personnel p LEFT JOIN FETCH p.educations WHERE p.id = :id")
    Personnel findByIdWithEducations(@Param("id") Long id);

    @Query("SELECT e FROM Education e LEFT JOIN FETCH e.experiences WHERE e.id IN :educationIds")
    List<Education> findEducationsWithExperiences(@Param("educationIds") List<Long> educationIds);

}
