package com.sao.personneleducation.repository;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.entity.Education;
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
public interface EducationRepository extends JpaRepository<Education, Long> {


    /**
     * Bu metot, belirtilen personele ait Eğitim bilgilerini çekerken,
     * bunlara bağlı Experience listesini de (e.experiences) tek bir veritabanı sorgusunda yükler.
     * 'LEFT JOIN FETCH' kullanımı, N+1 sorgu problemini ve LazyInitializationException hatasını önler.
     *
     * @param personnelId Personel ID'si
     * @return Eğitim ve ilişkili deneyim bilgilerini içeren tam bir liste.
     */
    @Query("SELECT e FROM Education e LEFT JOIN FETCH e.experiences WHERE e.personnelId = :personnelId")
    List<Education> getEducationByPersonnelIdWithExperiences(@Param("personnelId") Long personnelId);


}
