package com.sao.threadperformance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 23 May 2025
 * <p>
 * @description:
 */
@Entity
@Table(name="experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @EqualsAndHashCode.Include
    private Education education;
}
