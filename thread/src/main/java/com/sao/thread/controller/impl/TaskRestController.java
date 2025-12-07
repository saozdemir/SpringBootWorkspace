package com.sao.thread.controller.impl;

import com.sao.thread.controller.ITaskRestController;
import com.sao.thread.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 01 Ara 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/threads")
public class TaskRestController implements ITaskRestController {
    @Autowired
    private ITaskService taskService;

    @GetMapping(path = "/platform/{count}")
    @Override
    public String platformThreadTaskManager(@PathVariable int count){
        try {
            return taskService.platformThreadTaskManager(count);
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/virtual/{count}")
    @Override
    public String virtualThreadTaskManager(@PathVariable int count) {
        try {
            return taskService.virtualThreadTaskManager(count);
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }
}
