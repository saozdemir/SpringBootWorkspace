package com.sao.personnel.integration;

import com.sao.personnel.dto.TaskDto;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
public interface ITaskWebService {

    List<TaskDto> getTaskByPersonnelId(Long personnelId);
}
