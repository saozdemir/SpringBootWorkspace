package com.sao.controller;

import com.sao.jwt.AuthRequest;
import com.sao.model.dto.UserDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
public interface IRestAuthController {
    UserDto register(AuthRequest request);
}
