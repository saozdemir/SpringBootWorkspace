package com.sao.controller.impl;

import com.sao.controller.IRestAuthController;
import com.sao.jwt.AuthRequest;
import com.sao.jwt.AuthResponse;
import com.sao.model.dto.UserDto;
import com.sao.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    IAuthService authService;

    @PostMapping("/register")
    @Override
    public UserDto register(@Valid @RequestBody AuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
}
