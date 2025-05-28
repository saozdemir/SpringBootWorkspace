package com.sao.personneltask.controller;

import com.sao.personneltask.entity.Task;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
public interface ITaskController {

    List<Task> getTaskByPersonnelId(Long personnelId);

}
