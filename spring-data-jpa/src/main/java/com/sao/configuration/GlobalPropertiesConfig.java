package com.sao.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 02 Şub 2025
 * <p>
 * @description: @ConfigurationProperties anotasyonu kullanarak application.prperties dosyasında java class'ı oluşturacak şekilde değer okuma.
 */
@Component /** bean oluşması için*/
@Data /** getter setter oluşması için*/
@ConfigurationProperties(prefix = "app") /** app hepsinde . ya kadar ortak alan.*/
public class GlobalPropertiesConfig {
    private List<Server> servers; /** servers tipinde içinde ip ve location alanları olan bir liste. Bunu Server adında bir class listesi olarak ifade ettik. */

    @Value("${key}") /** @Value anotasyonu ile tek bir değer okumak istersek bu şekilde ikisi birlikte kullanılabilir.*/
    private String key;
}
