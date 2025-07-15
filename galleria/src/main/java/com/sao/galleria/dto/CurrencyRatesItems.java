package com.sao.galleria.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRatesItems {

    /**
     * Dışarıdan gelen JSON verisinde "Tarih" alanı maplemek için bu şekilde adlandırıyoruz.
     * Yani date alanı JSON'da "Tarih" olarak geçiyor.
     */
    @JsonProperty("Tarih")
    private String date;

    @JsonProperty("TP_DK_USD_A")
    private String rate;
}
