package com.sao.thread.service.impl;

import com.sao.thread.common.Task;
import com.sao.thread.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 Ara 2025
 * <p>
 * @description:
 */
@Service
public class TaskService implements ITaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Override
    public String platformThreadTaskManager(int count) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        var latch = new CountDownLatch(count);
        var builder = Thread.ofPlatform().name("ThreadPlatform - ", 1);
        for (int i = 0; i < count; i++) {
            int t = i;
            Thread thread = builder.unstarted(() -> {
                Task.runTask(t);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        logger.info("-----------------------------------------");
        logger.info("Task count: " + count);
        logger.info("Total time taken for Platform Thread: "  + (endTime - startTime) + "ms");
        logger.info("-----------------------------------------");
        return "Total time taken for Platform Thread: " + (endTime - startTime) + " ms";
    }

    @Override
    public String virtualThreadTaskManager(int count) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        var latch = new CountDownLatch(count);
        var builder = Thread.ofVirtual().name("ThreadVirtual - ", 1);
        for (int i = 0; i < count; i++) {
            int t = i;
            Thread thread = builder.unstarted(() -> {
                Task.runTask(t);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        logger.info("-----------------------------------------");
        logger.info("Task count: " + count);
        logger.info("Total time taken for Virtual Thread: "  + (endTime - startTime) + "ms");
        logger.info("-----------------------------------------");
        return "Total time taken for Virtual Thread: " + (endTime - startTime) + " ms";
    }
}
