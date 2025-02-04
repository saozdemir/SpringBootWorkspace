package com.sao.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;

    private String name;

    private String location;
}
