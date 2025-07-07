package com.sao.galleria.service;

import com.sao.galleria.dto.AuthRequest;
import com.sao.galleria.dto.AuthResponse;
import com.sao.galleria.dto.UserDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
public interface IAuthenticationService {
    UserDto register(AuthRequest authRequest);

    AuthResponse authenticate(AuthRequest authRequest);

}
