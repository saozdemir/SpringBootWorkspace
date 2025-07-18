package com.sao.usermanagement.dto;

import com.sao.usermanagement.entity.Role;
import com.sao.usermanagement.enums.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto{

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private RoleType selectedRole;

    private Set<Role> roles;

    private boolean isActive;

}
