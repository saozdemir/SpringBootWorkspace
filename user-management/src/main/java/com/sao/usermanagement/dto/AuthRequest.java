package com.sao.usermanagement.dto;

import com.sao.usermanagement.enums.RoleType;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
public record AuthRequest(
        @NotEmpty
        String username,
        @NotEmpty
        String password,

        RoleType selectedRole) {

}
