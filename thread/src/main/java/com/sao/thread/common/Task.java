package com.sao.thread.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
