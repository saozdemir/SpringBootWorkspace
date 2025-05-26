package com.sao.personneleducation.repository;

import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

    @Query("SELECT p.educations FROM Personnel p WHERE p.id = :personnelId")
    List<Education> findEducationsByPersonnelId(@Param("personnelId") Long personnelId);

    @Query("SELECT p FROM Personnel p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:surname IS NULL OR LOWER(p.surname) LIKE LOWER(CONCAT('%', :surname, '%')))")
    List<Personnel> findByNameAndSurname(
            @Param("name") String name,
            @Param("surname") String surname);
}
