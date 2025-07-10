package com.sao.galleria.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 10 Jul 2025
 * <p>
 * @description:
 */
public record RefreshTokenRequest(
        @NotEmpty
        String refreshToken) {

}
