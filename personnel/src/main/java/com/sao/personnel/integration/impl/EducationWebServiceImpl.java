package com.sao.personnel.integration.impl;

import com.sao.personnel.dto.EducationDto;
import com.sao.personnel.dto.TaskDto;
import com.sao.personnel.integration.IEducationWebService;
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
 * @date 26 Haz 2025
 * <p>
 * @description:
 */
@Component
public class EducationWebServiceImpl implements IEducationWebService {

    // Spring tarafından yönetilen tek bir RestTemplate nesnesini enjekte ediyoruz.
    // Bu, bağlantı havuzunun verimli kullanılmasını sağlar.
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8090/api/education";

    @Autowired
    public EducationWebServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<EducationDto> getEducationByPersonnelId(Long personnelId) {
        // Her çağrıda yeni bir RestTemplate oluşturmak yerine, enjekte edilen nesneyi kullanıyoruz.
        String url = baseUrl + "/" + personnelId + "/educations";

        // Yapay bir gecikme ekleyerek I/O beklemesini simüle ediyoruz.
        // Bu, sanal thread'lerin avantajını daha net görmenizi sağlar.
        try {
            Thread.sleep(200); // 200 milisaniye bekle
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ResponseEntity<List<EducationDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EducationDto>>() {}
        );
        return response.getBody();
    }
}

