package com.sao.thread.controller;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 01 Ara 2025
 * <p>
 * @description:
 */
public interface ITaskRestController {

    String platformThreadTaskManager(int count) throws InterruptedException;

    String virtualThreadTaskManager(int count) throws InterruptedException;
}
