package com.sao.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 Şub 2025
 * <p>
 * @description: Bu sınıf Controller katmanından  önce bir Filter katmanı oluşturup
 * kullanıcının yetki kontrollerini yapar. Kullanıcının erişim yetkisi varsa Contoller katmanına erişimi sağlar.
 * Kullanıcın erişim yetkisi yoksa Controller katmanına erişime izin vermeyerek yetkisiz kullanımları filtreler.
 *
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    /** AppConfig sınıfında @Bean anotasyonu ile işaretlenerek beai'i oluşturulan servis inject edildi. */
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header;
        String token;
        String username;

        /** header = "Bearer kajkdMKKmMkÇÇÇç" gibi bir değer alır. Baştaki "Bearer " ifadesini çıkarmak gerekir.*/
        header = request.getHeader("Authorization");
        if(header == null) {
            filterChain.doFilter(request,response);
            return;
        }

        /** Header token başına "Bearer " ifadesi eklenmiş halidir. Dolayısıyla 7. indexten başlatılır.*/
        token = header.substring(7);

        try {
            username = jwtService.getUserNameByToken(token);

            if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
                /** Bu adımda kullanıcı adının veritabannda olup olmadığı kontrol edilir.
                 * Kendi oluşturduğumuz "User" entity sınıfı "UserDetails" sınıfını extend eder.
                 * "User" sınıfının bağlı olduğu "user" tablosunda kullanıcı adının olup olmadığı kontrol edilirç
                 * */
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                /** Kullanıcıyı veritabanında bulabildiyse ve token süresi geçerliyse*/
                if(userDetails != null && jwtService.isTokenExpired(token)){
                    /** Kişiyi security context'e konulur. Bu kişiyi api kullanabilir hale getirir.*/
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                    /** Doğrulanmış userDetails artık authentication'a eklenerek security context'e eklenecek hale getirdi.*/
                    authentication.setDetails(userDetails);

                    /** Security context e kullanıcı eklendi. Tüm erişim kontrolleri yapılmış oldu.
                     * Bu adımda doğrulanan authentication, security context'e eklenmeden controller ve service katmanına erişim yetkisi vermez.
                     * */
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ExpiredJwtException e) {
            System.out.println("Token süresi dolmuştur. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ele alınamayan hata oluştu. " + e.getMessage());
        }
        /** Bu adım süreci devam ettirmeye yarar. Bu yazılmazsa controller katmanına yönlendirme yapamaz.*/
        filterChain.doFilter(request,response);

    }
}
