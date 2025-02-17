package com.sao.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = "com.sao")
@EnableJpaRepositories(basePackages = "com.sao")
@ComponentScan(basePackages = "com.sao")
@EnableScheduling
@SpringBootApplication
public class PageableApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(PageableApplicationStarter.class, args);
	}

}
