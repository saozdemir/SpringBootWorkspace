package com.sao.usermanagement.controller;

import com.sao.usermanagement.dto.*;
import com.sao.usermanagement.dto.iu.UserDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
public interface IAuthenticationController {

    RootEntity<UserDto> register(UserDtoIU userDtoIu);

    RootEntity<AuthResponse> authenticate(AuthRequest input);

    RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
