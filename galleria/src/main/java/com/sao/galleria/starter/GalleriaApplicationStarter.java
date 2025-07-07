package com.sao.galleria.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.sao.galleria"})// Entityleri görmesini sağladık
@ComponentScan(basePackages = {"com.sao.galleria"})// RestController Service ve Repository anotasyonları ile işaretlenmiş sınıfları görmesini sağladık.
@EnableJpaRepositories(basePackages = {"com.sao.galleria"})// JpaRepositroy kullanırsak bu sınıfları görmesini sağladık.
public class GalleriaApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(GalleriaApplicationStarter.class, args);
	}

}
