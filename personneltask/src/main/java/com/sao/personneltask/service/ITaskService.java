package com.sao.personneltask.service;

import com.sao.personneltask.dto.TaskDto;
import com.sao.personneltask.entity.Task;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
public interface ITaskService {

    List<TaskDto> getTaskByPersonnelId(Long personnelId);

    String generateLoadTestData();

}
