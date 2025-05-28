package com.sao.personneleducation.integration;

import com.sao.personneleducation.dto.TaskDto;
import org.springframework.web.client.RestTemplate;

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
