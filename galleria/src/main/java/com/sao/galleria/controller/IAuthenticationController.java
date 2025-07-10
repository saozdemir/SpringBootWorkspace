package com.sao.galleria.controller;

import com.sao.galleria.dto.*;

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

    RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
