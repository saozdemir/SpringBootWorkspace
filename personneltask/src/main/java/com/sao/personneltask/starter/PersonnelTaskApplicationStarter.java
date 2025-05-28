package com.sao.personneltask.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.sao.personneltask"})// Entityleri görmesini sağladık
@ComponentScan(basePackages = {"com.sao.personneltask"})// RestController Service ve Repository anotasyonları ile işaretlenmiş sınıfları görmesini sağladık.
@EnableJpaRepositories(basePackages = {"com.sao.personneltask"})// JpaRepositroy kullanırsak bu sınıfları görmesini sağladık.
public class PersonnelTaskApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(PersonnelTaskApplicationStarter.class, args);
    }

}
