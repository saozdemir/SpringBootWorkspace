package com.sao.galleria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldCarDto extends BaseDto{

    private CustomerDto customer;

    private GalleristDto gallerist;

    private CarDto car;
}
