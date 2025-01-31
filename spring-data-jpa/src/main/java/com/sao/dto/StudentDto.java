package com.sao.dto;

import com.sao.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description: Get ile sorgulama işlemlerinde bu sınıf kullanacak.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    // BeanUitls copy porperties il fied kopyalamayı çalıştırmak için fielt lara aynı isim verilmelidir.
    private Integer id;
    private String name;
    private String surname;
    private List<CourseDto> courses = new ArrayList<>();
}
