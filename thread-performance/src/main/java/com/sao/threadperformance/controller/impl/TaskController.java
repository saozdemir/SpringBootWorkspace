package com.sao.threadperformance.controller.impl;

import com.sao.threadperformance.controller.ITaskController;
import com.sao.threadperformance.entity.Task;
import com.sao.threadperformance.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/thread-api")
public class TaskController implements ITaskController {

    @Autowired
    private ITaskService taskService;
    private final Executor virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
    private final Executor platformThreadExecutor = Executors.newFixedThreadPool(200);

    // Virtual Thread Endpoint
    @GetMapping("/virtual/search")
    public CompletableFuture<List<Task>> virtualThreadSearch(@RequestParam(value = "q", required = false) String q) {
        return CompletableFuture.supplyAsync(() -> taskService.searchTasks(q), virtualThreadExecutor);
    }

    // Platform Thread Endpoint
    @GetMapping("/platform/search")
    public CompletableFuture<List<Task>> platformThreadSearch(@RequestParam(value = "q", required = false) String q) {
        return CompletableFuture.supplyAsync(() -> taskService.searchTasks(q), platformThreadExecutor);
    }

    // Test verisi oluşturma
    @PostMapping("/create-test-data")
    public String createTestData() {
        for (int i = 1; i <= 10000; i++) {
            taskService.createTask("Task-" + i);
        }
        return "10,000 test verisi oluşturuldu";
    }
}
