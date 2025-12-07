package com.sao.thread.service;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 01 Ara 2025
 * <p>
 * @description:
 */
public interface ITaskService {
    String platformThreadTaskManager(int count) throws InterruptedException;

    String virtualThreadTaskManager(int count) throws InterruptedException;
}
