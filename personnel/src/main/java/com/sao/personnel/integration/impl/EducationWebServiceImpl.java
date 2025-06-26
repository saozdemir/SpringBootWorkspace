package com.sao.personnel.integration.impl;

import com.sao.personnel.dto.EducationDto;
import com.sao.personnel.dto.TaskDto;
import com.sao.personnel.integration.IEducationWebService;
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
 * @date 26 Haz 2025
 * <p>
 * @description:
 */
@Component
public class EducationWebServiceImpl implements IEducationWebService {
    private final RestTemplateBuilder builder;
//    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8090/api/education";

    public EducationWebServiceImpl(RestTemplateBuilder builder) {
        this.builder = builder;
//        this.restTemplate = builder.build();
    }

    @Override
    public List<EducationDto> getEducationByPersonnelId(Long personnelId) {
        RestTemplate restTemplate = builder.build();
        String url = baseUrl + "/" + personnelId + "/educations";
        ResponseEntity<List<EducationDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EducationDto>>() {
                }
        );
        return response.getBody();
    }
}
