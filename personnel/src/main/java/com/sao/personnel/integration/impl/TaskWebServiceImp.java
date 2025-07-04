package com.sao.personnel.integration.impl;

import com.sao.personnel.dto.TaskDto;
import com.sao.personnel.integration.ITaskWebService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Spring tarafından yönetilen tek bir RestTemplate nesnesini enjekte ediyoruz.
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8091/api/task";

    @Autowired
    public TaskWebServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TaskDto> getTaskByPersonnelId(Long personnelId) {
        // Her çağrıda yeni bir RestTemplate oluşturmak yerine, enjekte edilen nesneyi kullanıyoruz.
        String url = baseUrl + "/" + personnelId + "/tasks";

        // Yapay bir gecikme ekleyerek I/O beklemesini simüle ediyoruz.
        // Bu, sanal thread'lerin avantajını daha net görmenizi sağlar.
        try {
            Thread.sleep(300); // 300 milisaniye bekle
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ResponseEntity<List<TaskDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TaskDto>>() {}
        );
        return response.getBody();
    }
}
