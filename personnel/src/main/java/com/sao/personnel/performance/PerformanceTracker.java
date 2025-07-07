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
    private static class Metrics {
        long memoryBefore;
        long cpuTimeBefore;
        Instant start;
        int availableProcessors;
    }

    private final Runtime runtime = Runtime.getRuntime();
    private final OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private final ThreadLocal<Metrics> threadMetrics = ThreadLocal.withInitial(Metrics::new);

    public void start() {
        Metrics metrics = threadMetrics.get();
        metrics.memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        metrics.cpuTimeBefore = osBean.getProcessCpuTime();
        metrics.start = Instant.now();
        metrics.availableProcessors = osBean.getAvailableProcessors();
    }

    public void stop(String label) {
        Metrics metrics = threadMetrics.get();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long cpuTimeAfter = osBean.getProcessCpuTime();
        Instant end = Instant.now();

        long elapsedMillis = Duration.between(metrics.start, end).toMillis();
        long elapsedNanos = elapsedMillis * 1_000_000L;

        long cpuUsedNanos = cpuTimeAfter - metrics.cpuTimeBefore;
        double cpuUsagePercent = elapsedNanos > 0 && metrics.availableProcessors > 0
                ? (cpuUsedNanos * 100.0) / (elapsedNanos * metrics.availableProcessors)
                : 0.0;

        long ramUsedBytes = memoryAfter - metrics.memoryBefore;
        ramUsedBytes = Math.max(ramUsedBytes, 0);

        double ramUsedMB = ramUsedBytes / (1024.0 * 1024);

        System.out.printf("▶ [%s] Thread: %s | Time: %d ms | RAM: %.2f MB | CPU: %.2f%%%n",
                label, Thread.currentThread().getName(), elapsedMillis, ramUsedMB, cpuUsagePercent);

        threadMetrics.remove();
    }
}
