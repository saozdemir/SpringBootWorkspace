package com.sao.threadperformance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description:
 */
@Entity
@Table(name="task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    private LocalDate creationDate;
}
