package com.sao.personnel.performance;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.time.Duration;
/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 27 Haz 2025
 * <p>
 * @description:
 */
public class ResourceTracker {
    private final Runtime runtime = Runtime.getRuntime();
    private final OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private long memoryBefore;
    private long cpuTimeBefore;
    private Instant start;

    public void start() {
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        cpuTimeBefore = osBean.getProcessCpuTime();
        start = Instant.now();
    }

    public void stop(String label) {
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long cpuTimeAfter = osBean.getProcessCpuTime();
        Instant end = Instant.now();

        long durationMillis = Duration.between(start, end).toMillis();
        long cpuUsedNanos = cpuTimeAfter - cpuTimeBefore;
        double cpuUsedMillis = cpuUsedNanos / 1_000_000.0;

        long ramUsedBytes = memoryAfter - memoryBefore;
        double ramUsedMB = ramUsedBytes / (1024.0 * 1024);

        System.out.printf("▶ [%s] Süre: %d ms, CPU süresi: %.2f ms, RAM değişimi: %.2f MB%n",
                label, durationMillis, cpuUsedMillis, ramUsedMB);
    }
}
