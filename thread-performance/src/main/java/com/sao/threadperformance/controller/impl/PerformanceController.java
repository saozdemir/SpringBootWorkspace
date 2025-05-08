package com.sao.threadperformance.controller.impl;

import com.sao.threadperformance.controller.IPerformanceController;
import com.sao.threadperformance.service.IPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description: Performans testi için kullanılan controller sınıfıdır.
 *  Bu sınıf, performans testi için gerekli olan endpoint'leri içerir.
 */
@RestController
@RequestMapping("/performance-api")
public class PerformanceController implements IPerformanceController {

    @Autowired
    private IPerformanceService performanceService;

    @GetMapping("/io-test/virtual")
    public String ioTestVirtual() throws Exception {
        return performanceService.simulateIOVirtual();
    }

    @GetMapping("/io-test/platform")
    public String ioTestPlatform() throws Exception {
        return performanceService.simulateIOPlatform();
    }

    @GetMapping("/cpu-test/virtual")
    public String cpuTestVirtual() throws Exception {
        return performanceService.simulateCPUVirtual();
    }

    @GetMapping("/cpu-test/platform")
    public String cpuTestPlatform() throws Exception {
        return performanceService.simulateCPUPlatform();
    }
}
