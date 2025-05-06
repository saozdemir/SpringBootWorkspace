package com.sao.threadperformance.service;

import com.sao.threadperformance.entity.Task;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description:
 */
public interface ITaskService {
    List<Task> findAllTasks();

    List<Task> searchTasks(String prefix);

    Task createTask(String name);
}
