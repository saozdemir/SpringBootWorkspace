package com.sao.galleria.dto;

import com.sao.galleria.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto extends BaseDto{

    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;
}
