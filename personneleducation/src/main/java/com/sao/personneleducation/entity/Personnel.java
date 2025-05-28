package com.sao.personneleducation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Entity
@Table(name = "personnel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Personnel entity model")
@ToString(exclude = "educations")
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Personnel unique identifier", example = "1")
    private Long id;

    @Column(name = "name")
    @Schema(description = "Personel adı", example = "Ahmet")
    private String name;

    @Column(name = "surname")
    @Schema(description = "Personel soyadı", example = "Özdemir")
    private String surname;

    @Schema(description = "Personel Eğirim Programı listesi")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "personnel_education",
            joinColumns = @JoinColumn(name = "personnel_id"),
            inverseJoinColumns = @JoinColumn(name = "education_id")
    )
    @JsonManagedReference
    private List<Education> educations;
}
