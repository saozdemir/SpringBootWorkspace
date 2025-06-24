package com.sao.personneltask.service.impl;

import com.github.javafaker.Faker;
import com.sao.personneltask.entity.Task;
import com.sao.personneltask.repository.TaskRepository;
import com.sao.personneltask.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        Faker faker = new Faker(new Locale("tr"));
        List<Task> assignedTasks = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            // Assign 40 or 50 random educations to each personnel
            int minTask = 20;
            int maxTask = 50;
            int taskCount = (int) (Math.random() * (maxTask - minTask + 1)) + minTask;
            for( int j = 0; j < taskCount; j++) {
                Task task = new Task();
                task.setPersonnelId((long) i);
                task.setName(faker.job().field());
                task.setDescription(faker.lorem().fixedString(30));
                task.setStatus(faker.options().option("Planlı", "Devam Ediyor", "Tamamlandı"));
                assignedTasks.add(task);
            }
            taskRepository.saveAll(assignedTasks);
        }
        return "Create Load Test Task Data successfully: " +
                "1000 Personnel, " + assignedTasks.size() + " Tasks records created.";
    }
}
