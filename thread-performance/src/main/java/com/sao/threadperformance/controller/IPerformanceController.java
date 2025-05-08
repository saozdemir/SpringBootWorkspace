package com.sao.threadperformance.controller;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description: Bu arayüz, performans testi için gerekli olan metotları tanımlar.
 */
public interface IPerformanceController {
    String ioTestVirtual() throws Exception;

    String ioTestPlatform() throws Exception;

    String cpuTestVirtual() throws Exception;

    String cpuTestPlatform() throws Exception;
}
