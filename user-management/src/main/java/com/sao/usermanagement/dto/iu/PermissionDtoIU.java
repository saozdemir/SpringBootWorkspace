package com.sao.usermanagement.dto.iu;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Data
public class PermissionDtoIU {

    @NotNull
    private String name;

    @NotNull
    private String code;

    private String description;
}
