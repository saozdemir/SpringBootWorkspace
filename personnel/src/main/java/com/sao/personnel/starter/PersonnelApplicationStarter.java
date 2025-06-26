package com.sao.personnel.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.sao.personnel"})// Entityleri görmesini sağladık
@ComponentScan(basePackages = {"com.sao.personnel"})// RestController Service ve Repository anotasyonları ile işaretlenmiş sınıfları görmesini sağladık.
@EnableJpaRepositories(basePackages = {"com.sao.personnel"})// JpaRepositroy kullanırsak bu sınıfları görmesini sağladık.
public class PersonnelApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(PersonnelApplicationStarter.class, args);
    }

}
