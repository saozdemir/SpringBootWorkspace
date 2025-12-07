package com.sao.thread.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 01 Ara 2025
 * <p>
 * @description:
 */
public class Task {
    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    public static void runTask(int i) {
        try {
//            logger.info("Starting Task: {} Thread Info: {}", i, Thread.currentThread());
            Thread.sleep(1000);
//            logger.info("Ending Task: {} Thread Info: {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
