package com.sao.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "com.sao")
@ComponentScan(basePackages = "com.sao")
@SpringBootApplication
public class BackendApiApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(BackendApiApplicationStarter.class, args);
	}

}
