package com.sao.usermanagement.service.impl;

import com.sao.usermanagement.dto.PermissionGroupDto;
import com.sao.usermanagement.dto.iu.PermissionGroupDtoIU;
import com.sao.usermanagement.entity.Permission;
import com.sao.usermanagement.entity.PermissionGroup;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.exception.ErrorMessage;
import com.sao.usermanagement.exception.MessageType;
import com.sao.usermanagement.mapper.PermissionGroupMapper;
import com.sao.usermanagement.repository.PermissionGroupRepository;
import com.sao.usermanagement.repository.PermissionRepository;
import com.sao.usermanagement.service.IPermissionGroupService;
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
public class PermissionGroupServiceImpl implements IPermissionGroupService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionGroupMapper permissionGroupMapper;

    private PermissionGroup createPermissionGroup(PermissionGroupDtoIU permissionGroupDtoIu) {
        PermissionGroup permissionGroup = permissionGroupMapper.toEntityFromDtoIu(permissionGroupDtoIu);
        permissionGroup.setCreateTime(new Date());
        permissionGroup.setPermissions(findPermissions(permissionGroupDtoIu.getPermissionIds()));
        return permissionGroup;
    }

    private Set<Permission> findPermissions(Set<Long> permissionIds) {
        List<Permission> permissions = permissionRepository.findAllById(permissionIds);
        return permissions.stream().map(permission -> permission).collect(Collectors.toSet());
    }

    @Override
    public PermissionGroupDto savePermissionGroup(PermissionGroupDtoIU permissionGroupDtoIU) throws BaseException {
        try {
            PermissionGroup savedPermissionGroup = permissionGroupRepository.save(createPermissionGroup(permissionGroupDtoIU));
            return permissionGroupMapper.toDto(savedPermissionGroup);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
    }
}
