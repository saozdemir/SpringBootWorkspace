package com.sao.galleria.config;

import com.sao.galleria.handler.AuthEntryPoint;
import com.sao.galleria.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refresh-token";


    /**
     * DaoAuthenticationProvider nesnesi burada kullanıldı.
     */
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    /**
     * Yukarıda tanımlanan REGISTER, AUTHENTICATE ve REFRESH_TOKEN endpoint'lerine erişim izni verir.
     * Diğer tüm istekler için kimlik doğrulama gerektirir ve controller katmanına erişim için JWT doğrulaması yapar.
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN)
                                .permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authEntryPoint)) //Token süresi dolmuş veya geçersiz ise 401 hata mesajı döner.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
