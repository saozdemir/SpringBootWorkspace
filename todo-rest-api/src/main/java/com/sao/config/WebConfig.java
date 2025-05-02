package com.sao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description: https://chatgpt.com/share/67915bcc-3eb4-8011-becb-5336ab171942
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final String[] allowedOrigins = {
            "http://localhost:5173",
            "http://127.0.0.1:5173",
            "http://localhost:5174",
            "http://127.0.0.1:5174",
            "http://localhost:1420",
            "http://127.0.0.1:1420"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)//.allowedOrigins("http://localhost:5173") // React uygulamasının URL'si//127.0.0.1
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
        //.allowedOrigins("*") // Tüm origin'lere izin verir
    }
}
