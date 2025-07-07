package com.sao.galleria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public final class RootEntity<T> {
    private Integer status;

    private T payload;

    private T errorMessage;

    public static <T> RootEntity<T> ok(T payload) {
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(HttpStatus.OK.value());
        rootEntity.setPayload(payload);
        rootEntity.setErrorMessage(null);
        return rootEntity;
    }


    public static <T> RootEntity<T> error(T errorMessage) {
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        rootEntity.setPayload(null);
        rootEntity.setErrorMessage(errorMessage);
        return rootEntity;
    }
}
