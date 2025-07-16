package com.sao.usermanagement.dto;

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
public class PermissionGroupDto extends BaseDto {
    private String name;

    private String description;

    private Set<PermissionDto> permissions;
}
