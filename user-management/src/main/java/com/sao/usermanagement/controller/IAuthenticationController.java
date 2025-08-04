package com.sao.usermanagement.controller;

import com.sao.usermanagement.dto.*;
import com.sao.usermanagement.dto.iu.UserDtoIU;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
public interface IAuthenticationController {

    RootEntity<UserDto> register(UserDtoIU userDtoIu);

    RootEntity<AuthResponse> authenticate(AuthRequest input, HttpServletResponse response);

    RootEntity<AuthResponse> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
