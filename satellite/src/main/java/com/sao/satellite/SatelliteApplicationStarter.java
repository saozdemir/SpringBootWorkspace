package com.sao.satellite;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sao.satellite.dto.Satellite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"com.sao"})// Entityleri görmesini sağladık
@ComponentScan(basePackages = {"com.sao"})// RestController Service ve Repository anotasyonları ile işaretlenmiş sınıfları görmesini sağladık.
@EnableJpaRepositories(basePackages = {"com.sao"})// JpaRepositroy kullanırsak bu sınıfları görmesini sağladık.
public class SatelliteApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(SatelliteApplicationStarter.class, args);
        String url = "https://celestrak.org/NORAD/elements/gp.php?GROUP=active&FORMAT=json";

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            System.out.println("CELESTRAK Sitesinden Veriler Çekiliyor Lütfen Bekleyin...");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String body = response.body();
                System.out.println("Veri Başarılı Şekilde Çekildi.");

                Gson gson = new Gson();
                Type satelliteTypeList = new TypeToken<List<Satellite>>() {}.getType();
                List<Satellite> activeSatellites = gson.fromJson(body, satelliteTypeList);
                System.out.println("Veri Dönüşümü Başarılı.");
                System.out.println("Toplam Aktif Uydu Sayısı: " + activeSatellites.size());

                for (int i=0; i<5; i++) {
                    System.out.println((i+1) + "- " + activeSatellites.get(i));
                }
            }
            else {
                System.out.println("Bağlantı Hatası! HTTP Kod: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Uyduları alırken bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
