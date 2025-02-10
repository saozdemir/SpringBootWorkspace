package com.sao.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 10 Şub 2025
 * <p>
 * @description: Yetki ile ilgili yani token ile ilgili bir hata alınırsa buraya girecek. Spring AuthenticationEntryPoint
 * interface'ini implement eden sınıf içinde commence metoduna yetki ile ilgili bir durum oluştuğunda otomatik olarak gider.
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
