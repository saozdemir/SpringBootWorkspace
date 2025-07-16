package com.sao.usermanagement.dto.iu;

import com.sao.usermanagement.entity.PermissionGroup;
import com.sao.usermanagement.enums.RoleType;
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
public class RoleDtoIU {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private RoleType type;

    private Set<Long> permissionGroupIds;
}
