package com.sao.personneltask.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Task entity model")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Task unique identifier", example = "1")
    private Long id;

    @Column(name = "name")
    @Schema(description = "Görev  adı", example = "Toplantı")
    private String name;

    @Column(name = "status")
    @Schema(description = "Görev  durumu", example = "Yapılacak")
    private String status;

    @Column(name = "description")
    @Schema(description = "Açıklama", example = "Toplantı yapılması gereken görev")
    private String description;

    @Column(name = "personnel_id")
    @Schema(description = "Personel ID", example = "Personel ID")
    private Long personnelId;
}
