package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.ICurrencyRatesController;
import com.sao.galleria.dto.CurrencyRatesResponse;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "api/galleria/currency-rates")
public class CurrencyRatesControllerImpl extends BaseController implements ICurrencyRatesController {

    @Autowired
    private ICurrencyRatesService currencyRatesService;


    @GetMapping(path = "/get")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        try {
            return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
