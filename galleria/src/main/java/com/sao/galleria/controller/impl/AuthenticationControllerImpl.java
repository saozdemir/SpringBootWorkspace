package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.IAuthenticationController;
import com.sao.galleria.dto.AuthRequest;
import com.sao.galleria.dto.AuthResponse;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.UserDto;
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

    @PostMapping("/register")
    @Override
    public RootEntity<UserDto> register(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.register(input));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.authenticate(input));
    }
}
