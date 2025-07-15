package com.sao.galleria.service;

import com.sao.galleria.dto.CurrencyRatesResponse;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
public interface ICurrencyRatesService {
    CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}
