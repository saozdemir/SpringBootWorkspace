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
@Deprecated(since = "Use ResourceTracker1 instead for more accurate CPU usage tracking.")
public class ResourceTracker2 {
    private final OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private final Runtime runtime = Runtime.getRuntime();

    private long startTimeNano;
    private long startCpuTimeNano;
    private long startUsedMemory;

    public void start() {
        startTimeNano = System.nanoTime();
        startCpuTimeNano = osBean.getProcessCpuTime();
        startUsedMemory = runtime.totalMemory() - runtime.freeMemory();
    }

    public void stop(String label) {
        long endTimeNano = System.nanoTime();
        long endCpuTimeNano = osBean.getProcessCpuTime();
        long endUsedMemory = runtime.totalMemory() - runtime.freeMemory();

        long elapsedTimeMs = (endTimeNano - startTimeNano) / 1_000_000;
        double cpuUsedMs = (endCpuTimeNano - startCpuTimeNano) / 1_000_000.0;
        double ramUsedMB = (endUsedMemory - startUsedMemory) / (1024.0 * 1024);

        // Ortalama CPU kullanımı hesaplanıyor
//        double cpuUsagePercent = (elapsedTimeMs == 0) ? 0.0 : (cpuUsedMs / elapsedTimeMs) * 100;
        double cpuUsagePercent = (elapsedTimeMs == 0) ? 0.0 : (cpuUsedMs / (elapsedTimeMs * osBean.getAvailableProcessors())) * 100;

        System.out.printf("📊 [%s] İşlem süresi boyunca %.2f MB RAM ve %.2f%% CPU kullanılmıştır.%n",
                label, ramUsedMB, cpuUsagePercent);
    }
}
