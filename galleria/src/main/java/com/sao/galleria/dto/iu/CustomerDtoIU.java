package com.sao.galleria.dto.iu;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Data
public class CustomerDtoIU {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    private Date birthOfDate;

    @NotNull
    private Long addressId;

    @NotNull
    private Long accountId;
}
