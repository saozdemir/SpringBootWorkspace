package com.sao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String name;
    private String surname;
}
