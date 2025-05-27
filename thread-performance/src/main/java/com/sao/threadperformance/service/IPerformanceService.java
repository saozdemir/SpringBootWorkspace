package com.sao.threadperformance.service;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description:
 */
public interface IPerformanceService {
    String simulateIOVirtual() throws Exception;

    String simulateIOPlatform() throws Exception;

    String simulateCPUVirtual() throws Exception;

    String simulateCPUPlatform() throws Exception;
}
