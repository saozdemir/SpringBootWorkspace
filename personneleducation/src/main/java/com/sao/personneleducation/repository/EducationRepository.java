package com.sao.personneleducation.repository;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface EducationRepository extends JpaRepository<Education, Long> {

    List<Education> getEducationByPersonnelId(Long personnelId);
}
