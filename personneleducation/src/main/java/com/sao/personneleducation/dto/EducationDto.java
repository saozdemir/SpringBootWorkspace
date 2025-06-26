package com.sao.personneleducation.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sao.personneleducation.entity.Experience;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
public class EducationDto {
    private Long id;

    private String name;

    private List<ExperienceDto> experiences;
}
