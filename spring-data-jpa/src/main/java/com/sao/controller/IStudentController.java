package com.sao.controller;

import com.sao.dto.StudentDto;
import com.sao.dto.StudentDtoIU;
import com.sao.entities.Student;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
public interface IStudentController {
    public StudentDto saveStudent(StudentDtoIU studentDtoUI);

    public List<StudentDto> getAllStudents();

    public StudentDto getStudentById(Integer id);

    public boolean deleteStudent(Integer id);

    public StudentDto updateStudent(Integer id, StudentDtoIU updateStudent);
}
