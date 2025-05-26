package com.sao.personneleducation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany(mappedBy = "educations")
    @Schema(description = "İlgili Ödev Listesi")
    @JsonBackReference
    private List<Personnel> personnelList;
}
