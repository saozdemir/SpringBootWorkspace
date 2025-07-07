package com.sao.personnel.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 05 Tem 2025
 * <p>
 * @description:
 */

@Aspect
@Component
public class ResourceProfilingAspect {

    private static final Runtime runtime = Runtime.getRuntime();

    @Around("@annotation(ResourceProfiled)")
    public Object profileResources(ProceedingJoinPoint joinPoint) throws Throwable {
        // CPU başlangıcı
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        com.sun.management.OperatingSystemMXBean sunOsBean =
                (com.sun.management.OperatingSystemMXBean) osBean;

        long cpuStartTime = sunOsBean.getProcessCpuTime();
        long startTime = System.nanoTime();
        long startUsedMem = usedMemory();

        Object result = joinPoint.proceed(); // İşlemi çalıştır

        long endTime = System.nanoTime();
        long cpuEndTime = sunOsBean.getProcessCpuTime();
        long endUsedMem = usedMemory();

        // Süre ve kaynak hesaplama
        long elapsedTimeMs = (endTime - startTime) / 1_000_000;
        long usedMemMB = (endUsedMem - startUsedMem) / (1024 * 1024);
        double cpuLoadPercent = calculateCpuUsage(cpuStartTime, cpuEndTime, elapsedTimeMs);

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        System.out.printf("Servis işlemini tamamladı (%s). Tüketilen toplam kaynaklar: %d MB RAM, %.2f%% CPU, Süre: %d ms%n",
                method.getName(), usedMemMB, cpuLoadPercent, elapsedTimeMs);

        return result;
    }

    private long usedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private double calculateCpuUsage(long cpuStartTime, long cpuEndTime, long elapsedTimeMs) {
        int availableProcessors = runtime.availableProcessors();
        long cpuTimeDiff = cpuEndTime - cpuStartTime; // nanoseconds
        long elapsedTimeNs = elapsedTimeMs * 1_000_000;
        double cpuUsage = (double) cpuTimeDiff / (elapsedTimeNs * availableProcessors);
        return cpuUsage * 100.0;
    }
}