package com.sao.galleria.service.impl;

import com.sao.galleria.dto.CurrencyRatesResponse;
import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description: https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A&startDate=14-07-2025&endDate=14-07-2025&type=json
 * Api Key: hXPQBnzWvA
 */
@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {
    public static final String ROOT_URL = "https://evds2.tcmb.gov.tr/service/evds/";
    public static final String SERIES = "TP.DK.USD.A";
    public static final String TYPE = "json";
    public static final String API_KEY = "hXPQBnzWvA";

    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String endpoint = String.format("%sseries=%s&startDate=%s&endDate=%s&type=%s",
                ROOT_URL, SERIES, startDate, endDate, TYPE);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", API_KEY);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint,
                    HttpMethod.GET,
                    httpEntity,
                    ParameterizedTypeReference.forType(CurrencyRatesResponse.class));
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (RestClientException e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_SERVICE_NOT_AVAILABLE, e.getMessage()));
        }
        return null;
    }
}
