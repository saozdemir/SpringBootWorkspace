package com.sao.repository;

import com.sao.model.entity.Personnel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 17 Şub 2025
 * <p>
 * @description:
 */
@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

    /**
     * Pageable içine setlenen değere göre verileri çekecek.
     * Geriye Personnel tipinde Page yapısı dönecek.
     */
    //@Query(value = "from Personnel") /** Personel tablosundaki tüm kayıtları getir.*/
    //@Query(value = "select p from Personnel p", countQuery = "select count(p) from Personnel p") // :SQL
    @Query(value = "select p from Personnel p", countQuery = "select count(p) from Personnel p") // : HQL
    Page<Personnel> findAllPageable(Pageable pageable); //data.domain

    //Page<Personnel> findAll(Pageable pageable);
}
