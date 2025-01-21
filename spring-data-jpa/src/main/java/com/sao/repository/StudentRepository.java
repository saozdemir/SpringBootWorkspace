package com.sao.repository;

import com.sao.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description: CRUD işlemleri için Hibernate altyapısını kullanacağız.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> { //CrudRepository<Student, Integer> de kullanılabilir.

}
