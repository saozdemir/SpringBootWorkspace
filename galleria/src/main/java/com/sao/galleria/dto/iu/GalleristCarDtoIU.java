package com.sao.galleria.dto.iu;

import lombok.Data;
import lombok.NonNull;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
@Data
public class GalleristCarDtoIU {

    @NonNull
    private Long galleristId;

    @NonNull
    private Long carId;
}
