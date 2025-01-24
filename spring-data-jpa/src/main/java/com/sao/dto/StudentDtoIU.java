package com.sao.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
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
    @NotEmpty(message = "İsim alanı boş bırakılamaz!")
    @Min(value = 3)
    @Max(value = 10)
    private String name;

    @Size(min = 2, max = 10)
    private String surname;

    private Date birthOfDate;

    @Email(message = "Email formatında bir adres giriniz!")
    private String email;

    @Size(min = 11, max = 11, message = "TCKN 11 karakter olmalıdır.")
    @NotEmpty
    private String tckn;
}
