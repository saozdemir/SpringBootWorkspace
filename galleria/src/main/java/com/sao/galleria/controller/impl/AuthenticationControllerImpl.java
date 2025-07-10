package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.IAuthenticationController;
import com.sao.galleria.dto.*;
import com.sao.galleria.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
@RestController
public class AuthenticationControllerImpl extends BaseController implements IAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping(path = "/register")
    @Override
    public RootEntity<UserDto> register(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.register(input));
    }

    @PostMapping(path = "/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.authenticate(input));
    }

    @PostMapping(path = "/refresh-token")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
