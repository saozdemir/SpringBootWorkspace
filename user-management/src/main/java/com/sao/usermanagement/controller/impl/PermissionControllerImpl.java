package com.sao.usermanagement.controller.impl;

import com.sao.usermanagement.controller.BaseController;
import com.sao.usermanagement.controller.IPermissionController;
import com.sao.usermanagement.dto.PermissionDto;
import com.sao.usermanagement.dto.RootEntity;
import com.sao.usermanagement.dto.iu.PermissionDtoIU;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.service.IPermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/user-management/permission")
public class PermissionControllerImpl extends BaseController implements IPermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<PermissionDto> savePermission(@Valid @RequestBody PermissionDtoIU permissionDtoIu) {
        try {
            return ok(permissionService.savePermission(permissionDtoIu));
        } catch (BaseException e) {
            return error(e.getMessage());
        }
    }
}
