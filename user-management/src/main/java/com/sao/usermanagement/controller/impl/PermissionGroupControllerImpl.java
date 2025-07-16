package com.sao.usermanagement.controller.impl;

import com.sao.usermanagement.controller.BaseController;
import com.sao.usermanagement.controller.IPermissionGroupController;
import com.sao.usermanagement.dto.PermissionGroupDto;
import com.sao.usermanagement.dto.RootEntity;
import com.sao.usermanagement.dto.iu.PermissionGroupDtoIU;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.service.IPermissionGroupService;
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
@RequestMapping(path = "/api/user-management/permission-groups")
public class PermissionGroupControllerImpl extends BaseController implements IPermissionGroupController {

    @Autowired
    IPermissionGroupService permissionGroupService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<PermissionGroupDto> savePermissionGroup(@Valid @RequestBody PermissionGroupDtoIU permissionGroupDtoIu) throws Exception {
        try {
            return ok(permissionGroupService.savePermissionGroup(permissionGroupDtoIu));
        } catch (BaseException e) {
            return error(e.getMessage());
        }
    }
}
