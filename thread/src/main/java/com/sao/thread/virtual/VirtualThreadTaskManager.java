package com.sao.thread.virtual;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 01 Ara 2025
 * <p>
 * @description:
 */
public class VirtualThreadTaskManager {
    private static final int VIRTUAL_THREAD_TASK = 10000;

    public static void main(String[] args) {
        try {
            runVirtualThreadTasks();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runVirtualThreadTasks() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        var latch = new java.util.concurrent.CountDownLatch(VIRTUAL_THREAD_TASK);
        var builder = Thread.ofVirtual().name("ThreadVirtual - ", 1);
        for (int i = 0; i < VIRTUAL_THREAD_TASK; i++) {
            int t = i;
            Thread thread = builder.unstarted(() -> {
                com.sao.thread.common.Task.runTask(t);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken for Virtual Thread: " + (endTime - startTime) + " ms");
    }
}
