package com.sao.controller.impl;

import com.sao.model.RootEntity;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description:
 */
public class RestBaseController {

    public <T> RootEntity<T> ok(T data) {
        return RootEntity.ok(data);
    }

    public <T> RootEntity<T> error(T errorMessage) {
        return RootEntity.error(errorMessage);
    }
}
