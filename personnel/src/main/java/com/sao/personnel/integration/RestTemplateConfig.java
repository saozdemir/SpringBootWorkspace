package com.sao.personnel.integration;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Jul 2025
 * <p>
 * @description:
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Apache HttpClient 5 tabanlı bir bağlantı havuzu yöneticisi oluşturuyoruz.
        // Bu, bağlantıların verimli bir şekilde yeniden kullanılmasını sağlar.
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // Toplamda aynı anda açık olabilecek maksimum bağlantı sayısı.
        connectionManager.setMaxTotal(400);
        // Her bir hedef sunucu (route) için aynı anda açık olabilecek maksimum bağlantı sayısı.
        connectionManager.setDefaultMaxPerRoute(200);

        // Bağlantı yöneticisini kullanarak HttpClient'ı oluşturuyoruz.
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        // RestTemplate'in Apache HttpClient'ı kullanmasını sağlıyoruz.
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        // RestTemplate'i bu yeni konfigürasyon ve zaman aşımları ile oluşturuyoruz.
        return builder
                .requestFactory(() -> requestFactory).connectTimeout(Duration.ofSeconds(200)).readTimeout(Duration.ofSeconds(400))
                .build();
    }


//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        // Bağlantı ve okuma zaman aşımları belirlemek her zaman iyi bir pratiktir.
//        return builder.connectTimeout(Duration.ofSeconds(200)).readTimeout(Duration.ofSeconds(400))
//                .build();
//
//    }
}
