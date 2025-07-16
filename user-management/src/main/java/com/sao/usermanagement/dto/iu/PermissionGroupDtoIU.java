package com.sao.usermanagement.dto.iu;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@Data
public class PermissionGroupDtoIU {

    @NotNull
    private String name;

    private String description;

    private Set<Long> permissionIds;
}
