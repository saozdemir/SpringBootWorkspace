package com.sao.threadperformance.service.impl;

import com.sao.threadperformance.service.IPerformanceService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description: Performans testi için kullanılan service sınıfıdır.
 *  Bu sınıf, performans testi için gerekli olan metotları içerir.
 */

@Service
public class PerformanceService implements IPerformanceService {
    private final ExecutorService platformExecutor = Executors.newFixedThreadPool(50);
    private final ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    public String simulateIOVirtual() throws Exception {
        Instant start = Instant.now();
        virtualExecutor.submit(() -> {
            try {
                Thread.sleep(200); // I/O simulasyonu (veritabanı bekleme gibi)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).get(); // bloklama yapılır

        Duration duration = Duration.between(start, Instant.now());
        return "IO Bounds: Completed in " + duration.toMillis() + " ms using virtual threads.";
    }

    public String simulateIOPlatform() throws Exception {
        Instant start = Instant.now();
        platformExecutor.submit(() -> {
            try {
                Thread.sleep(200); // I/O simulasyonu (veritabanı bekleme gibi)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).get(); // bloklama yapılır

        Duration duration = Duration.between(start, Instant.now());
        return "IO Bounds: Completed in " + duration.toMillis() + " ms using platform threads.";
    }

    public String simulateCPUVirtual() throws Exception {
        Instant start = Instant.now();
        virtualExecutor.submit(() -> {
            long sum = 0;
            for (long i = 0; i < 500_000_000L; i++) {
                sum += i;
            }
            return sum;
        }).get();

        Duration duration = Duration.between(start, Instant.now());
        return "CPU Bounds: Completed in " + duration.toMillis() + " ms using virtual threads.";
    }

    public String simulateCPUPlatform() throws Exception {
        Instant start = Instant.now();
        platformExecutor.submit(() -> {
            long sum = 0;
            for (long i = 0; i < 500_000_000L; i++) {
                sum += i;
            }
            return sum;
        }).get();

        Duration duration = Duration.between(start, Instant.now());
        return "CPU Bounds: Completed in " + duration.toMillis() + " ms using platform threads.";
    }

}
