package com.sao.service;

import com.sao.jwt.AuthRequest;
import com.sao.jwt.AuthResponse;
import com.sao.model.dto.UserDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
public interface IAuthService {
    UserDto register(AuthRequest request);

    AuthResponse authenticate(AuthRequest request);
}
