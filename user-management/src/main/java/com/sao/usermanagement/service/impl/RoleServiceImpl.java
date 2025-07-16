package com.sao.usermanagement.service.impl;

import com.sao.usermanagement.dto.RoleDto;
import com.sao.usermanagement.dto.iu.RoleDtoIU;
import com.sao.usermanagement.entity.PermissionGroup;
import com.sao.usermanagement.entity.Role;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.exception.ErrorMessage;
import com.sao.usermanagement.exception.MessageType;
import com.sao.usermanagement.mapper.RoleMapper;
import com.sao.usermanagement.repository.PermissionGroupRepository;
import com.sao.usermanagement.repository.RoleRepository;
import com.sao.usermanagement.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    private RoleMapper roleMapper;

    private Role createRole(RoleDtoIU roleDtoIu) {
        Role role = roleMapper.toEntityFromDtoIu(roleDtoIu);
        role.setCreateTime(new Date());
        role.setPermissionGroups(findPermissionGroups(roleDtoIu.getPermissionGroupIds()));
        return role;
    }

    private Set<PermissionGroup> findPermissionGroups(Set<Long> permissionGroupIds) {
        List<PermissionGroup> permissionGroups = permissionGroupRepository.findAllById(permissionGroupIds);
        return permissionGroups.stream().map(permissionGroup -> permissionGroup).collect(Collectors.toSet());
    }

    @Override
    public RoleDto saveRole(RoleDtoIU roleDtoIu) throws BaseException {
        try {
            Role savedRole = roleRepository.save(createRole(roleDtoIu));
            return roleMapper.toDto(savedRole);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
    }
}
