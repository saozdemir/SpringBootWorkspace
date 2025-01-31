package com.sao.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 31 Oca 2025
 * <p>
 * @description: Bu sınıf Spring uygulaması ilk ayağa kalkarken
 * application.properties dosyasının içeriğini okuyarak @Value anotasyonu ile işaratli alanlara yazar.
 *
 */
@Data
@Component
public class GlobalProperties {
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
}
