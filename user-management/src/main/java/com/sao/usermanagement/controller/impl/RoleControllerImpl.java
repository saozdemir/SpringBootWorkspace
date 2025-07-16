package com.sao.usermanagement.controller.impl;

import com.sao.usermanagement.controller.BaseController;
import com.sao.usermanagement.controller.IRoleController;
import com.sao.usermanagement.dto.RoleDto;
import com.sao.usermanagement.dto.RootEntity;
import com.sao.usermanagement.dto.iu.RoleDtoIU;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.service.IRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/user-management/roles")
public class RoleControllerImpl extends BaseController implements IRoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<RoleDto> saveRole(@Valid @RequestBody RoleDtoIU roleDtoIu) {
        try {
            return ok(roleService.saveRole(roleDtoIu));
        } catch (BaseException e) {
            return error(e.getMessage());
        }
    }
}
