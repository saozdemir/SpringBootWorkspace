package com.sao.personnel.performance;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;
/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 27 Haz 2025
 * <p>
 * @description:
 */
@Deprecated(since = "Use PerformanceTracker instead for more accurate CPU usage tracking.")
public class ResourceTracker1 {
    private final Runtime runtime = Runtime.getRuntime();
    private final OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private long memoryBefore;
    private long cpuTimeBefore;
    private Instant start;

    private int availableProcessors;

    public void start() {
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        cpuTimeBefore = osBean.getProcessCpuTime(); // nanoseconds
        start = Instant.now();
        availableProcessors = osBean.getAvailableProcessors();
    }

    public void stop(String label) {
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long cpuTimeAfter = osBean.getProcessCpuTime();
        Instant end = Instant.now();

        long elapsedMillis = Duration.between(start, end).toMillis();
        long elapsedNanos = elapsedMillis * 1_000_000;

        long cpuUsedNanos = cpuTimeAfter - cpuTimeBefore;
        double cpuUsagePercent = (cpuUsedNanos * 100.0) / (elapsedNanos * availableProcessors);

        long ramUsedBytes = memoryAfter - memoryBefore;
        double ramUsedMB = ramUsedBytes / (1024.0 * 1024);

        System.out.printf("▶ [%s] Süre: %d ms işlem süresi boyunca %.2f MB RAM ve %.2f%% CPU kullanılmıştır.%n",
                label, elapsedMillis, ramUsedMB, cpuUsagePercent);
    }
}
