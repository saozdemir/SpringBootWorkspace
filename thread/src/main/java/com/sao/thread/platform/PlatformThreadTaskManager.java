package com.sao.thread.platform;

import com.sao.thread.common.Task;

import java.util.concurrent.CountDownLatch;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 01 Ara 2025
 * <p>
 * @description:
 */
public class PlatformThreadTaskManager {
    private static final int PLATFORM_THREAD_TASK = 10000;

    public static void main(String[] args) {
        try {
            runPlatformThreadTasks();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runPlatformThreadTasks() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        var latch = new CountDownLatch(PLATFORM_THREAD_TASK);
        var builder = Thread.ofPlatform().name("ThreadPlatform - ", 1);
        for (int i = 0; i < PLATFORM_THREAD_TASK; i++) {
            int t = i;
            Thread thread = builder.unstarted(() -> {
                Task.runTask(t);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken for Platform Thread: " + (endTime - startTime) + " ms");
    }
}
