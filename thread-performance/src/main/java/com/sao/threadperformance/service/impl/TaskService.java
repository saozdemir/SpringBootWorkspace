package com.sao.threadperformance.service.impl;

import com.sao.threadperformance.entity.Task;
import com.sao.threadperformance.repository.TaskRepository;
import com.sao.threadperformance.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description:
 */
@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> searchTasks(String prefix) {
        return taskRepository.findByNameContaining(prefix);
    }

    @Transactional
    public Task createTask(String name) {
        Task task = new Task();
        task.setName(name);
        return taskRepository.save(task);
    }
}
