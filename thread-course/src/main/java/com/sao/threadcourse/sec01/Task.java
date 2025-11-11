package com.sao.threadcourse.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Kas 2025
 * <p>
 * @description:
 */
public class Task {
    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    public static void ioIntensive (int i) {
        try {
            logger.info("Starting IO Task: {}", i);
            Thread.sleep(Duration.ofSeconds(60));
            logger.info("Ending IO Task: {}", i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
