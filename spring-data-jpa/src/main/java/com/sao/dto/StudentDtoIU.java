package com.sao.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description: Insert Update işlemlerinde bu sınıf kullanılacak.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDtoIU {
    private String name;
    private String surname;
    private Date birthOfDate;
}
