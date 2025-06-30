package com.sao.personnel.performance;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Haz 2025
 * <p>
 * @description:
 */
@Component
public class PerformanceTracker {
    private final Runtime runtime = Runtime.getRuntime();
    private final OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private long memoryBefore;
    private long cpuTimeBefore;
    private Instant start;
    private int availableProcessors;

    public void start() {
        memoryBefore = runtime.maxMemory() - runtime.freeMemory();
        cpuTimeBefore = osBean.getProcessCpuTime();
        start = Instant.now();
        availableProcessors = osBean.getAvailableProcessors();
    }

    public void stop(String label) {
        long memoryAfter = runtime.maxMemory() - runtime.freeMemory();
        long cpuTimeAfter = osBean.getProcessCpuTime();
        Instant end = Instant.now();

        long elapsedMillis = Duration.between(start, end).toMillis();
        long elapsedNanos = elapsedMillis * 1_000_000;

        long cpuUsedNanos = cpuTimeAfter - cpuTimeBefore;
        double cpuUsagePercent = (cpuUsedNanos * 100.0) / (elapsedNanos * availableProcessors);

        long ramUsedBytes = memoryAfter - memoryBefore;
        ramUsedBytes = Math.max(ramUsedBytes, 0);

        double ramUsedMB = ramUsedBytes / (1024.0 * 1024);

        System.out.printf("▶ [%s] Süre: %d ms | RAM: %.2f MB | CPU: %.2f%%%n",
                label, elapsedMillis, ramUsedMB, cpuUsagePercent);
    }
}
