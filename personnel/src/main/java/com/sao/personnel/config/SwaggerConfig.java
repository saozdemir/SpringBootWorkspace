package com.sao.personnel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 * Swsagger Çalıştırmak için
 * {@link "http://localhost:8080/swagger-ui.html"}
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Personel API Dokümantasyonu")
                        .version("1.0")
                        .description("Personnel, Education ve Experience yönetimi için Spring Boot API")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("API Destek")
                                .url("https://example.com/contact")
                                .email("support@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
