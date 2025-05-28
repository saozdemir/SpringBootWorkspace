package com.sao.personneltask.controller.impl;

import com.sao.personneltask.controller.ITaskController;
import com.sao.personneltask.entity.Task;
import com.sao.personneltask.service.ITaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/task")
@Tag(name = "Görev", description = "Personel Görev yönetim API")
public class TaskControllerImpl implements ITaskController {

    @Autowired
    private ITaskService taskService;

    @GetMapping(path = "/{personnelId}/tasks")
    @Override
    public List<Task> getTaskByPersonnelId(@PathVariable Long personnelId) {
        return taskService.getTaskByPersonnelId(personnelId);
    }
}
