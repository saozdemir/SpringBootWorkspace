package com.sao.usermanagement.service;

import com.sao.usermanagement.dto.PermissionDto;
import com.sao.usermanagement.dto.iu.PermissionDtoIU;
import com.sao.usermanagement.exception.BaseException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
public interface IPermissionService {

    PermissionDto savePermission(PermissionDtoIU permissionDtoIu) throws BaseException;

}
