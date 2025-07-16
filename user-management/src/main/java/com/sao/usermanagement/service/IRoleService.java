package com.sao.usermanagement.service;

import com.sao.usermanagement.dto.RoleDto;
import com.sao.usermanagement.dto.iu.RoleDtoIU;
import com.sao.usermanagement.exception.BaseException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
public interface IRoleService {

    RoleDto saveRole(RoleDtoIU roleDtoIu) throws BaseException;

}
