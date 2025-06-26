package com.sao.personnel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sao.personnel.dto.EducationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) /** Bu sınıf içinde null set edilen alan varsa geri null geri dönme*/
public class PersonnelDto {
    private Long id;

    private String name;

    private String surname;

    private List<TaskDto> tasks;

    private List<EducationDto> educations;
}
