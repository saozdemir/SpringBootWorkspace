package com.sao.usermanagement.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sao.usermanagement.dto.RootEntity;
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
 * @date 16 Jul 2025
 * <p>
 * @description: Yetkili bir kimlik doğrulama girişimi başarısız olduğunda çağrılır.
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        RootEntity<Object> errorResponse = RootEntity.error(authException.getMessage(), response.getStatus());
        String json = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(json);
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
