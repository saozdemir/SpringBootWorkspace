package com.sao.personneleducation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Entity
@Table(name = "experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Experience entity model")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Experience unique identifier", example = "1")
    private Long id;

    @Column(name = "name")
    @Schema(description = "Ödev adı", example = "Senior Developer")
    private String name;

    @Column(name = "score")
    @Schema(description = "Ödev notu (1-10)", example = "8")
    private int score;

    @ManyToOne
    @JoinColumn(name = "education_id")
    @Schema(description = "İlgili Eğitim Programı")
    @JsonBackReference
    private Education education;
}
