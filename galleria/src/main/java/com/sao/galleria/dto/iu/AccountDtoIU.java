package com.sao.galleria.dto.iu;

import com.sao.galleria.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public class AccountDtoIU {
    @NotNull
    private String accountNo;

    @NotNull
    private String iban;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private CurrencyType currencyType;
}
