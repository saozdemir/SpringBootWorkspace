package com.sao.config;

import com.sao.model.User;
import com.sao.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Oca 2025
 * <p>
 * @description: AppConfig sınıfı bean'lerin tutulacağı bir contextir. (Yani AppConfig kase, userService kasedeki fasulye)
 */
 @Configuration
public class AppConfig {

    /**
     * Bean anotasyonu çok yaygın değil daha çok aşağıdaki anotasyonlar ile bean oluşturuluyor.
     * RestController,  Repository, Service (Sterotype adı veriliyor.)
     */
     @Bean
     public UserService userService() {
         UserService userService = new UserService();
         List<User> userList = new ArrayList<>();

         userList.add(new User("Ahmet"));
         userList.add(new User("Emir"));

         userService.setUserList(userList);
         return userService;
     }
}
