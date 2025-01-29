package com.sao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Oca 2025
 * <p>
 * @description:
 */
@Entity
@Table(name = "home")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;


    @OneToMany
    private List<Room> room;
}
