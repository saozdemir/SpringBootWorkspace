package com.sao.personneltask.service.impl;

import com.sao.personneltask.entity.Task;
import com.sao.personneltask.repository.TaskRepository;
import com.sao.personneltask.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    @Override
    public String generateLoadTestData() {

        for (int i = 11; i < 1011; i++) {
            // Assign 40 or 50 random educations to each personnel
            int minTask = 20;
            int maxTask = 50;
            int taskCount = (int) (Math.random() * (maxTask - minTask + 1)) + minTask;
//            Task task = new Task();
//            task.setPersonnelId((long) personId);
//            task.setTaskName("Task " + i);
//            task.setDescription("Description for task " + i);
//            task.setStatus("Pending");
//            taskRepository.save(task);
        }
        return "";
    }
}
