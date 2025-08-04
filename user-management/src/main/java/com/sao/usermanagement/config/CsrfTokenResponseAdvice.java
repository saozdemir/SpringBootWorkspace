package com.sao.usermanagement.config;

import com.sao.usermanagement.controller.IBaseController;
import com.sao.usermanagement.dto.RootEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Aug 2025
 * <p>
 * @description:Bu sınıf, RootEntity tipindeki tüm API cevaplarını yakalar
 * ve JSON'a çevrilmeden hemen önce, cevabın içine güncel CSRF token'ını otomatik olarak ekler.
 */
@RestControllerAdvice(assignableTypes = IBaseController.class)
public class CsrfTokenResponseAdvice implements ResponseBodyAdvice<RootEntity> {

    /**
     * Bu metod, advice'ın hangi tür cevaplar için çalışacağını belirler.
     * Sadece RootEntity tipindeki cevaplar için devreye girmesini istiyoruz.
     * @return true, eğer cevap RootEntity tipindeyse; aksi halde false.
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().isAssignableFrom(RootEntity.class);
    }

    /**
     * Bu metod, cevap body'si istemciye gönderilmeden hemen önce çalışır.
     * Burada body'yi manipüle ederek csrf token eklenebilir.
     * @param body Controller'dan dönen RootEntity nesnesi.
     * @return Manipüle edilmiş (içine csrfToken eklenmiş) RootEntity nesnesi.
     */
    @Override
    public RootEntity beforeBodyWrite(RootEntity body, MethodParameter returnType, MediaType selectedContentType,
                                      Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                      ServerHttpRequest request, ServerHttpResponse response) {

        // Spring'in ServerHttpRequest'inden standart HttpServletRequest'i alıyoruz.
        if (request instanceof ServletServerHttpRequest servletServerHttpRequest) {
            HttpServletRequest httpServletRequest = servletServerHttpRequest.getServletRequest();

            // Request attribute'larından CSRF token'ını çekiyoruz.
            CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());

            if (csrfToken != null) {
                // RootEntity nesnesinin csrfToken alanına değeri set ediyoruz.
                body.setCsrfToken(csrfToken.getToken());
            }
        }

        return body;
    }
}
