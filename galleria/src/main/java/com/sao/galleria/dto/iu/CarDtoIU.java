package com.sao.galleria.dto.iu;

import com.sao.galleria.enums.CarStatusType;
import com.sao.galleria.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Jul 2025
 * <p>
 * @description:
 */
@Data
public class CarDtoIU {

    @NotNull
    private String licensePlate;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Integer productionYear;

    @NotNull
    private BigDecimal price;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;
}
