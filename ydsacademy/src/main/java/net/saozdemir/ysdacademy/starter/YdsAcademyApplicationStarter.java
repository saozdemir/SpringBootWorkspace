package net.saozdemir.ysdacademy.starter;

import net.saozdemir.ysdacademy.model.dto.CategoryDto;
import net.saozdemir.ysdacademy.model.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "net.saozdemir.ysdacademy")
@EntityScan(basePackages = "net.saozdemir.ysdacademy")
@EnableJpaRepositories(basePackages = "net.saozdemir.ysdacademy")
public class YdsAcademyApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(YdsAcademyApplicationStarter.class, args);
    }

}
