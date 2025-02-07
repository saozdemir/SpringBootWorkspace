package com.sao.model.dto;

import com.sao.model.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;

    private String name;

    private String surname;

    private DepartmentDto department;
}
