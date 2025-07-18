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
//@Data
public record AuthRequest(
        @NotEmpty
        String username,
        @NotEmpty
        String password) {
}
