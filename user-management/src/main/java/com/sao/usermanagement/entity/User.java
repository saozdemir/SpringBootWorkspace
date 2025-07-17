package com.sao.usermanagement.entity;

import com.sao.usermanagement.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    @Enumerated(EnumType.STRING)
    private RoleType selectedRole;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().filter(role -> role.getType().equals(selectedRole))
                .flatMap(role -> role.getPermissionGroups().stream())
                .flatMap(group -> group.getPermissions().stream())
                .map(permission -> (GrantedAuthority) () -> permission.getCode())
                .collect(Collectors.toSet());

//        return roles.stream()
//                .flatMap(role -> role.getPermissionGroups().stream())
//                .flatMap(group -> group.getPermissions().stream())
//                .map(permission -> (GrantedAuthority) () -> permission.getCode())
//                .collect(Collectors.toSet());
    }
}
