package com.sao.config;

import com.sao.jwt.AuthEntryPoint;
import com.sao.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description: Bu sınıf erişim kontrolleri ve kayıt işlemlerini istisna tutarak contoller katmanına erişim izni
 * vermek amacı ile kullanılacaktır.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String AUTHENTICATE = "/authenticate";
    public static final String REGISTER = "/register";
    public static final String REFRESH_TOKEN = "/refreshToken";
    public static final String[] SWAGGER_PATH = {
            "/swagger-ui/**",
            "/doc-my-rest-api/**",
            "/v3/api-docs/**",
            "/swagger-ui.html"
    };

    /**
     * AppConfig sınıfında bu bean tanımlanmıştı burada enjekte edildi.
     */
    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     * JwtAuthenticationFilter sınıfı spring
     */
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Yetki ile ilgili hata anlınması durumunda kullanılacak
     */
    @Autowired
    private AuthEntryPoint authEntryPoint;

    /**
     * "/authenticate" ve "/register" path ine bir istek gelirse filter katmanını geçip conroller katmanına erişmesi gerekiyor.
     * Bu metot ile bu işlemi yaptırıyoruz.
     * Bu adımın nedeni kullanıcı adı ve şifre doğrulamasına göre bir token üretecek olmamız.
     * Register için ise yeni kayıt olacağından mecburen filter adımını geçmesi gerekmektedir.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers(AUTHENTICATE, REGISTER, REFRESH_TOKEN)
                                .permitAll()/** "/authenticate" ve "/register"  adreslerine istek gelirse es geç filter'a gimesin.*/
                                .requestMatchers(SWAGGER_PATH).permitAll()/** Swagger adresine token olmadan erişime izin ver*/
                                .anyRequest()
                                .authenticated()) /** Yukarıda tanımlanan iki istisna haricindeki istekleri filter katmanında yetki kontrollerini yap*/
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider) /** AppConfig içinde oluşturulan AuthenticationPorvider buraya verildi.*/
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) /**JwtAuthenticationFilter sınıfını enjekte ederek filter katmanının önüne koyduk.*/
                .exceptionHandling(exception->{
                    exception.authenticationEntryPoint(authEntryPoint); /** Yetki ile ilgili hata alınırsa AuthEntryConfig sınıfı içinde commence metoduna git.*/
                });
        return http.build();
    }
}
