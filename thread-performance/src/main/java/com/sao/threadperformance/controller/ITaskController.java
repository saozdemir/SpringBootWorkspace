package com.sao.threadperformance.controller;

import com.sao.threadperformance.entity.Task;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description:
 */
public interface ITaskController {
    CompletableFuture<List<Task>> virtualThreadSearch(String q);

    CompletableFuture<List<Task>> platformThreadSearch( String q);

    String createTestData();
}
