package com.sao.personneleducation.controller.impl;

import com.sao.personneleducation.controller.ITestDataController;
import com.sao.personneleducation.service.ITestDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/test-data")
@Tag(name = "Test Verisi", description = "Test verisi oluşturma API")
public class TestDataControllerImpl implements ITestDataController {
    @Autowired
    private ITestDataService testDataService;

    @PostMapping("/generate-load-test-data")
    @Operation(summary = "Yük Testi verilerini oluştur",
            description = "1000 Personnel, 100 Education ve 100 Experience kaydı oluşturur")
    @Override
    public String generateLoadTestData() {
        return testDataService.generateLoadTestData();
    }
}
