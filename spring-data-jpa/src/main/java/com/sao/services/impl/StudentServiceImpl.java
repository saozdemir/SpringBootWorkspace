package com.sao.services.impl;

import com.sao.dto.StudentDto;
import com.sao.dto.StudentDtoIU;
import com.sao.entities.Student;
import com.sao.repository.StudentRepository;
import com.sao.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDto saveStudent(StudentDtoIU studentDtoIU) {
        Student student = new Student();
        StudentDto responseStudent = new StudentDto();
        BeanUtils.copyProperties(studentDtoIU, student);
        Student dbStudent = studentRepository.save(student);
        BeanUtils.copyProperties(dbStudent, responseStudent);
        return responseStudent;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> dtoList = new ArrayList<>();
//        List<Student> studentList = studentRepository.findAll();
        /** @Query anotasyonu ile yazılan sorgu ile çeklek için kullandık.*/
        List<Student> studentList = studentRepository.findAllStudents();
        for (Student student : studentList) {
            StudentDto dto = new StudentDto();
            BeanUtils.copyProperties(student, dto);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public StudentDto getStudentById(Integer id) {
        StudentDto dto = new StudentDto();
        /** Aşağıdaki satır @Query anotasyonu ile yazılmış sorguyu test etmek için kullanıldı.*/
//        Student testQueryStudent = studentRepository.findStudentById(id);
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) { //!optional.isEmpty()
            Student dbStudent = optional.get();
            BeanUtils.copyProperties(dbStudent,dto);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteStudent(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            studentRepository.delete(optional.get());
            return true;
        }
        return false;
    }

    @Override
    public StudentDto updateStudent(Integer id, StudentDtoIU updatedStudent) {
        Optional<Student> optional = studentRepository.findById(id);
        StudentDto dto = new StudentDto();
        if (optional.isPresent()) {
            Student dbStudent = optional.get();
            dbStudent.setName(updatedStudent.getName());
            dbStudent.setSurname(updatedStudent.getSurname());
            dbStudent.setBirthOfDate(updatedStudent.getBirthOfDate());
            Student updatedDBStudent = studentRepository.save(dbStudent);
            BeanUtils.copyProperties(updatedDBStudent, dto);
            return dto;
        }
        return null;
    }
}
