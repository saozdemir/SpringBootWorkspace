package com.sao.galleria.controller;

import com.sao.galleria.dto.CurrencyRatesResponse;
import com.sao.galleria.dto.RootEntity;
import org.springframework.web.client.RestClientException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */public interface ICurrencyRatesController {

     RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
