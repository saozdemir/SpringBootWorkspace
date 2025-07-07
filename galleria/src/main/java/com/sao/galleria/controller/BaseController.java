package com.sao.galleria.controller;

import com.sao.galleria.dto.RootEntity;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
public abstract class BaseController {
    public <T> RootEntity<T> ok(T payload) {
        return RootEntity.ok(payload);
    }

    public <T> RootEntity<T> error(T errorMessage) {
        return RootEntity.error(errorMessage);
    }
}
