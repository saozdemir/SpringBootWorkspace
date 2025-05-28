package com.sao.threadperformance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 23 May 2025
 * <p>
 * @description:
 */
@Entity
@Table(name="education")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // hashCode ve equals için dahil et
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JsonIgnore
    private List<Personnel> personnelList = new ArrayList<>();

    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Experience> experiences = new HashSet<>();
}
