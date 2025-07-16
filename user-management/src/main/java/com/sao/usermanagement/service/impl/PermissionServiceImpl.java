package com.sao.usermanagement.service.impl;

import com.sao.usermanagement.dto.PermissionDto;
import com.sao.usermanagement.dto.iu.PermissionDtoIU;
import com.sao.usermanagement.entity.Permission;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.exception.ErrorMessage;
import com.sao.usermanagement.exception.MessageType;
import com.sao.usermanagement.mapper.PermissionMapper;
import com.sao.usermanagement.repository.PermissionRepository;
import com.sao.usermanagement.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    private Permission createPermission(PermissionDtoIU permissionDtoIu) {
        Permission permission = permissionMapper.toEntityFromDtoIu(permissionDtoIu);
        permission.setCreateTime(new Date());
        return permission;
    }


    @Transactional(rollbackFor = Error.class)
    @Override
    public PermissionDto savePermission(PermissionDtoIU permissionDtoIu) throws BaseException {
        try {
            Permission savedPermission = permissionRepository.save(createPermission(permissionDtoIu));
            return permissionMapper.toDto(savedPermission);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
    }
}
