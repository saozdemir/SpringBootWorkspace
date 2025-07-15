package com.sao.galleria.dto.iu;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
@Data
public class SoldCarDtoIU {

    @NotNull
    private Long customerId;

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;
}
