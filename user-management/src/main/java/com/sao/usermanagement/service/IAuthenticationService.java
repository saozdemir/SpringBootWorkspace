package com.sao.usermanagement.service;

import com.sao.usermanagement.dto.AuthRequest;
import com.sao.usermanagement.dto.AuthResponse;
import com.sao.usermanagement.dto.RefreshTokenRequest;
import com.sao.usermanagement.dto.UserDto;
import com.sao.usermanagement.dto.iu.UserDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
public interface IAuthenticationService {

    UserDto register(UserDtoIU userDtoIu);

    AuthResponse authenticate(AuthRequest authRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
