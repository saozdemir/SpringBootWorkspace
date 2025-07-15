package com.sao.galleria.dto;

import lombok.Data;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description: TCMB web servisinden dönen alanların tutulduğu DTO sınıfı.
 */
@Data
public class CurrencyRatesResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;

}
