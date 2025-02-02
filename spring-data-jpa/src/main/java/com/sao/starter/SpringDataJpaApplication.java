package com.sao.starter;

import com.sao.configuration.GlobalPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.sao"})// Entityleri görmesini sağladık
@ComponentScan(basePackages = {"com.sao"})// RestController Service ve Repository anotasyonları ile işaretlenmiş sınıfları görmesini sağladık.
@EnableJpaRepositories(basePackages = {"com.sao"})// JpaRepositroy kullanırsak bu sınıfları görmesini sağladık.
//@PropertySource(value = "classpath:app.properties") // İstediğimiz ismi verip o properties dosyasının kullanmaya olanak tanır.
@EnableConfigurationProperties(value = GlobalPropertiesConfig.class)
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

}
