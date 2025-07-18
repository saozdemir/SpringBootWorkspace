package com.sao.usermanagement.dto.iu;

import com.sao.usermanagement.entity.Role;
import com.sao.usermanagement.enums.RoleType;
import lombok.Data;

import java.util.Set;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Data
public class UserDtoIU {
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private RoleType selectedRole;

    private Set<Long> roleIds;
}
