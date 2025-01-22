package com.sao.controller.impl;

import com.sao.controller.IStudentController;
import com.sao.dto.StudentDto;
import com.sao.dto.StudentDtoIU;
import com.sao.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/rest/api/student")
public class StudentControllerImpl implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping("/save")
    @Override
    public StudentDto saveStudent(@RequestBody StudentDtoIU studentDtoIU) {
        return studentService.saveStudent(studentDtoIU);
    }

    @GetMapping(path = "/list")
    @Override
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public StudentDto getStudentById(@PathVariable(name = "id") Integer id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public boolean deleteStudent(@PathVariable(name = "id") Integer id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public StudentDto updateStudent(@PathVariable (name = "id") Integer id, @RequestBody StudentDtoIU updateStudent) {
        return studentService.updateStudent(id, updateStudent);
    }
}
