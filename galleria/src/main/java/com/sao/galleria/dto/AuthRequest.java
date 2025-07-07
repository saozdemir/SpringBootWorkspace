package com.sao.galleria.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
@Data
public class AuthRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
