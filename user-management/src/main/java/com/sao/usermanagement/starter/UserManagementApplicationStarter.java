package com.sao.usermanagement.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.sao.usermanagement"})// Entityleri görmesini sağladık
@ComponentScan(basePackages = {"com.sao.usermanagement"})// RestController Service ve Repository anotasyonları ile işaretlenmiş sınıfları görmesini sağladık.
@EnableJpaRepositories(basePackages = {"com.sao.usermanagement"})// JpaRepositroy kullanırsak bu sınıfları görmesini sağladık.
public class UserManagementApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplicationStarter.class, args);
	}

}
