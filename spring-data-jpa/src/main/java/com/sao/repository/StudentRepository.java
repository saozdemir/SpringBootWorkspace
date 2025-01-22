package com.sao.repository;

import com.sao.dto.StudentDto;
import com.sao.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description: CRUD işlemleri için Hibernate altyapısını kullanacağız.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> { //CrudRepository<Student, Integer> de kullanılabilir.
    //Yetenekler yeteriz kaldığında Query metotları ile kendi sorgularımızı yazacağız.

    // HQL : sınıfın ismi ve değişken isimleri kullanılarak sorgular yazılır. (Hibernate Query Language) nativeQuery = false
    // SQL : tablo ismi ve tablo içerisindeki kolon isimleri ile sorgular yazılır. nativeQuery = true
//    @Query(value = "from Student" , nativeQuery = false)
//    List<Student> findAllStudents();

    @Query(value = "select  * from student.student" , nativeQuery = true)//student şemasındaki student tablosuna git
    List<Student> findAllStudents();

    @Query(value = "from Student s where s.id=:searchId")
    Student findStudentById(Integer searchId);
//    Optional<Student> findStudentById(Integer searchId);// Bu şekilde dönüp JpaRepository yeteneği yerine de kullanabiliriz.
}
