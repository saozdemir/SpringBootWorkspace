package com.sao.threadperformance.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.sao.threadperformance"})// Entityleri görmesini sağladık
@ComponentScan(basePackages = {"com.sao.threadperformance"})// RestController Service ve Repository anotasyonları ile işaretlenmiş sınıfları görmesini sağladık.
@EnableJpaRepositories(basePackages = {"com.sao.threadperformance"})// JpaRepositroy kullanırsak bu sınıfları görmesini sağladık.
public class ThreadPerformanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPerformanceApplication.class, args);
    }

}
