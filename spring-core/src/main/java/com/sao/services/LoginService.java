package com.sao.services;

import com.sao.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Oca 2025
 * <p>
 * @description:
 */
public class LoginService {
    public void login() {
        //Kullanıcı listesine ihtiyaç olduğunu düşünelim
        //Bu sınıfa parametre olarak @Configuration ile işaretlenen sınıf parametre olarak verilir. (AppConfig.java)
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Context içinde tanımlanan bean'i getir.
        UserService userService = context.getBean(UserService.class);

    }
}
