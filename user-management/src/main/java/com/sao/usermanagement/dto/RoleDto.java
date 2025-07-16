package com.sao.usermanagement.dto;

import com.sao.usermanagement.entity.PermissionGroup;
import com.sao.usermanagement.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseDto {

    private String name;

    private String description;

    private RoleType type;

    private Set<PermissionGroup> permissionGroups;
}
