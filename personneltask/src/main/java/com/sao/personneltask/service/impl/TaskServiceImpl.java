package com.sao.personneltask.service.impl;

import com.sao.personneltask.entity.Task;
import com.sao.personneltask.repository.TaskRepository;
import com.sao.personneltask.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTaskByPersonnelId(Long personnelId) {
        return taskRepository.findByPersonnelId(personnelId);
    }
}
