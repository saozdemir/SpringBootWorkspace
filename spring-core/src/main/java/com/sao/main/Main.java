package com.sao.main;

import com.sao.config.AppConfig;
import com.sao.model.User;
import com.sao.services.LoginService;
import com.sao.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Oca 2025
 * <p>
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        //Bu sınıfa parametre olarak @Configuration ile işaretlenen sınıf parametre olarak verilir. (AppConfig.java)
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //xml ile de erişilebiliyor ama tercih edilmiyor. Anotasyon güncel yöntem.

        //Context içinde tanımlanan bean'i getir.
        UserService userService = context.getBean(UserService.class);
        for (User user : userService.getUserList()) {
            System.out.println(user);
        }


        //İki servis de kullanıcı listesini Context de tanımlanan bean üzerinden aldı.
        LoginService loginService = new LoginService();
        loginService.login();
    }
}
