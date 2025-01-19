package com.sao.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Component anotasyonun extend eden
 * 	@RestController, @Service ve @Repository anotasyonlarını kullanan sınıfları bu ve container da beanlerini oluştur.
 */
@ComponentScan(basePackages = {"com.sao"})
@SpringBootApplication
public class SpringRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiApplication.class, args);
	}

}
