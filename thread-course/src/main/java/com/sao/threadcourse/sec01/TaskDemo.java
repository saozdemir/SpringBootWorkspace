package com.sao.threadcourse.sec01;

import java.util.concurrent.CountDownLatch;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Kas 2025
 * <p>
 * @description:
 */
public class TaskDemo {

    private static final int MAX_PLATFORM_THREADS = 10;
    private static final int MAX_VIRTUAL_THREADS = 20;


    public static void main(String[] args) throws InterruptedException {
//        platformThreadDemo();
        virtualThreadDemo();
    }

    public static void platformThreadDemo1() {
        for (int i = 0; i < MAX_PLATFORM_THREADS; i++) {
            int t = i;
            Thread thread = new Thread(() -> Task.ioIntensive(t));
            thread.start();
        }
    }

    /** Builder kullanımı */
    public static void platformThreadDemo2() {
        var builder = Thread.ofPlatform().name("Thread - ", 1);
        for (int i = 0; i < MAX_PLATFORM_THREADS; i++) {
            int t = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(t));
            thread.start();
        }
    }

    /** daemon ve CountDownLatch kullanımı*/
    public static void platformThreadDemo() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        var latch = new CountDownLatch(MAX_PLATFORM_THREADS);
        var builder = Thread.ofPlatform().daemon().name("ThreadPlatform - ", 1);
        for (int i = 0; i < MAX_PLATFORM_THREADS; i++) {
            int t = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(t);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken for Platform Thread: " + (endTime - startTime) + " ms");
    }

    /** Virtual Thread Builder kullanımı */
    public static void virtualThreadDemo() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        var latch = new CountDownLatch(MAX_VIRTUAL_THREADS);
        var builder = Thread.ofVirtual().name("ThreadVirtual - ", 1);
        for (int i = 0; i < MAX_VIRTUAL_THREADS; i++) {
            int t = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(t);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken for Virtual Thread: " + (endTime - startTime) + " ms");
    }

}
