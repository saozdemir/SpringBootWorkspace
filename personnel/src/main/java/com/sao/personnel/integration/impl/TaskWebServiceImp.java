package com.sao.personnel.integration.impl;

import com.sao.personnel.dto.TaskDto;
import com.sao.personnel.integration.ITaskWebService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
@Component
public class TaskWebServiceImp implements ITaskWebService {
    private final RestTemplateBuilder builder;
//    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8091/api/task";

    public TaskWebServiceImp(RestTemplateBuilder builder) {
        this.builder = builder;
//        this.restTemplate = builder.build();
    }

    @Override
    public List<TaskDto> getTaskByPersonnelId(Long personnelId) {
        RestTemplate restTemplate = builder.build();
        String url = baseUrl + "/" + personnelId + "/tasks";
        ResponseEntity<List<TaskDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TaskDto>>() {
                }
        );
        return response.getBody();
    }
}
