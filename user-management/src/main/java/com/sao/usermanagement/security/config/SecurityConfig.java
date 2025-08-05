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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
                /** YENİ EKLENEN KISIM: CORS yapılandırmasını Security filtresine dahil ediyoruz. */
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
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
                         * bir endpoint olarak kabul ediliyor. Yani, bu endpoint'e erişim access token'ı olmayan kullancılar için istisna bırakıldı.
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

    /**
     * YENİ EKLENEN BEAN: CORS ayarlarını merkezi olarak yönetir.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // İzin verilen kaynak (React uygulamanızın çalıştığı adres)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173","http://127.0.0.1:5173"));

        // İzin verilen HTTP metotları
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // İzin verilen HTTP başlıkları (Authorization ve CSRF başlıkları dahil)
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-XSRF-TOKEN"));

        // Cookie'lerin ve kimlik bilgilerinin gönderilmesine izin ver.
        // React'teki "withCredentials: true" ayarı için bu ZORUNLUDUR!
        configuration.setAllowCredentials(true);

        // Tarayıcının ön kontrol (preflight) cevaplarını ne kadar süreyle cache'lemesi gerektiğini belirtir.
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Tüm yollar (/**) için yukarıdaki CORS yapılandırmasını geçerli kıl.
        source.registerCorsConfiguration("/**", configuration);

        return source;
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
