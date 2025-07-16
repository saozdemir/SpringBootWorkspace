package com.sao.usermanagement.controller.impl;

import com.sao.usermanagement.controller.BaseController;
import com.sao.usermanagement.controller.IAuthenticationController;
import com.sao.usermanagement.dto.*;
import com.sao.usermanagement.dto.iu.UserDtoIU;
import com.sao.usermanagement.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@RestController
public class AuthenticationControllerImpl extends BaseController implements IAuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping(path = "/register")
    @Override
    public RootEntity<UserDto> register(@Valid @RequestBody UserDtoIU userDtoIu) {
        try {
            return ok(authenticationService.register(userDtoIu));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @PostMapping(path = "/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
        try {
            return ok(authenticationService.authenticate(input));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @PostMapping(path = "/refresh-token")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
            return ok(authenticationService.refreshToken(refreshTokenRequest));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
