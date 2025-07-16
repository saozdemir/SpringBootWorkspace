package com.sao.usermanagement.controller;

import com.sao.usermanagement.dto.PermissionGroupDto;
import com.sao.usermanagement.dto.RootEntity;
import com.sao.usermanagement.dto.iu.PermissionGroupDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
public interface IPermissionGroupController {

    RootEntity<PermissionGroupDto> savePermissionGroup(PermissionGroupDtoIU permissionGroupDtoIu) throws Exception;

}
