package com.sao.galleria.dto.iu;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public class AddressDtoIU {
    @NotEmpty
    private String city;

    @NotEmpty
    private String district;

    @NotEmpty
    private String neighborhood;

    @NotEmpty
    private String street;
}
