package com.sao.personneleducation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "education")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Education entity model")
@ToString(exclude = "experiences")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Education unique identifier", example = "1")
    private Long id;

    @Column(name = "name")
    @Schema(description = "Eğitim Programı adı", example = "Computer Science")
    private String name;

    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Experience> experiences;

    @Column(name = "personnel_id")
    @Schema(description = "Personel ID", example = "Personel ID")
    private Long personnelId;

}
