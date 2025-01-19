package com.sao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 20 Oca 2025
 * <p>
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private long id;
    private String name;
    private String surname;
}
