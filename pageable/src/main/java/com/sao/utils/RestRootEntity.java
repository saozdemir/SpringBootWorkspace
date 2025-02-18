package com.sao.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 19 Şub 2025
 * <p>
 * @description:
 */
@Data
public class RestRootEntity <T>{
    private Integer status;

    private T payload;

    private String errorMessage;

    /**
     * Tüm servislerden dönen response standart hale getirildi.
     * @param payload
     * @return
     * @param <T>
     */
    public static <T> RestRootEntity<T> ok(T payload) {
        RestRootEntity<T> entity = new RestRootEntity<T>();
        entity.setStatus(HttpStatus.OK.value());
        entity.setPayload(payload);
        entity.setErrorMessage(null);
        return entity;
    }
}
