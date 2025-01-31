package com.sao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 21 Oca 2025
 * <p>
 * @description:
 */
@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    //    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birth_of_date", nullable = true)
    private Date birthOfDate;

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name="student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
