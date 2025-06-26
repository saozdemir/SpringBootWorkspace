package com.sao.personneltask.controller.impl;

import com.sao.personneltask.controller.ITaskController;
import com.sao.personneltask.dto.TaskDto;
import com.sao.personneltask.entity.Task;
import com.sao.personneltask.service.ITaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<TaskDto> getTaskByPersonnelId(@PathVariable Long personnelId) {
        return taskService.getTaskByPersonnelId(personnelId);
    }

    @PostMapping(path = "/generate-load-test-data")
    @Override
    public String generateLoadTestData() {
        return taskService.generateLoadTestData();
    }
}
