package com.sao.galleria.controller;

import com.sao.galleria.dto.AuthRequest;
import com.sao.galleria.dto.AuthResponse;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.UserDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
public interface IAuthenticationController {

    RootEntity<UserDto> register(AuthRequest input);

    RootEntity<AuthResponse> authenticate(AuthRequest input);
}
