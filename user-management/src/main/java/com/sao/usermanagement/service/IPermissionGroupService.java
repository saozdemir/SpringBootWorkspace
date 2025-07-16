package com.sao.usermanagement.service;

import com.sao.usermanagement.dto.PermissionGroupDto;
import com.sao.usermanagement.dto.iu.PermissionGroupDtoIU;
import com.sao.usermanagement.entity.PermissionGroup;
import com.sao.usermanagement.exception.BaseException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
public interface IPermissionGroupService {

    PermissionGroupDto savePermissionGroup(PermissionGroupDtoIU permissionGroupDtoIU) throws BaseException;

}
