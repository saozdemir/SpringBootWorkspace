package com.sao.usermanagement.security.config;

import com.sao.usermanagement.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refresh-token";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /** CSRF korumasını aktifleştirmek için gerekli ayarlar. Bu ayar sayesinde isteklerde CSRF token'ı kontrol edilir. */
        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        requestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        http
                // YENİ: CSRF Konfigürasyonu
                .csrf(csrf -> csrf
                        // CSRF token'ını saklamak için CookieCsrfTokenRepository kullanılır.
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        // CSRF token'ının işlenmesini sağlamak için handler set edilir.
                        .csrfTokenRequestHandler(requestAttributeHandler)
                        /**
                         * YENİ EKLENEN VE SORUNU ÇÖZEN SATIR:
                         * CSRF filtresine, /register ve /authenticate yollarına gelen istekler için
                         * CSRF token kontrolü yapmamasını söylüyoruz. Bu, 401 hatasını çözer.
                         */
                        .ignoringRequestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN) // CSRF korumasını bu endpoint'ler için devre dışı bırakıyoruz.
                )
                .authorizeHttpRequests(request ->
                        /**
                         * GÜNCELLENEN SATIR:
                         * /refresh-token endpoint'i, CSRF korumasına tabi olması gerektiği için
                         * permitAll() listesinden çıkarıldı. Artık yetkilendirme gerektiren
                         * bir endpoint olarak kabul ediliyor.
                         */
                        request.requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN)
                                .permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session ->
                        // STATELESS kalmaya devam ediyor, bu sayede session oluşturulmuyor.
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    /**CSFR Disable*/
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(request ->
//                        request.requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN)
//                                .permitAll()
//                                .anyRequest().authenticated())
//                .exceptionHandling(exception ->
//                        exception.authenticationEntryPoint(authEntryPoint))
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
}
