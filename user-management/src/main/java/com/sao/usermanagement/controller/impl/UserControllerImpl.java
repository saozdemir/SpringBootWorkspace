package com.sao.usermanagement.controller.impl;

import com.sao.usermanagement.controller.BaseController;
import com.sao.usermanagement.controller.IUserController;
import com.sao.usermanagement.dto.RootEntity;
import com.sao.usermanagement.dto.UserDto;
//import static com.sao.usermanagement.security.authorization.PermissionConstants.*;//TODO: 1. Yöntem

import com.sao.usermanagement.dto.iu.UserDtoIU;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.security.authorization.PermissionChecker;
import com.sao.usermanagement.security.authorization.PermissionConstants;
import com.sao.usermanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/user-management/user")
public class UserControllerImpl extends BaseController implements IUserController {

    @Autowired
    private IUserService userService;

//    @Autowired //TODO: 2. Yöntem
//    private PermissionChecker permissionChecker;

    //TODO: 1. Yöntem: @PreAuthorize ile
//    @PreAuthorize("@permissionChecker.hasPermission(authentication, T(com.sao.usermanagement.security.authorization.PermissionConstants).SEARCH_USER)")
    @GetMapping(path = "/list/{id}")
    @Override
    public RootEntity<UserDto> findUserById(@PathVariable(value = "id") Long id) {
        //TODO: 2. Yöntem: @Autowired ile
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        permissionChecker.hasPermission(authentication, SEARCH_USER);
        ////TODO: 3. Yöntem: static import ile
        boolean tets =PermissionChecker.checkPermission(PermissionConstants.SEARCH_USER);
        return ok(userService.findUserById(id));

    }

    @PutMapping(path = "/add-role-to-user")
    @Override
    public RootEntity<UserDto> addRoleToUser(@RequestBody UserDtoIU userDtoIu) {
        try {
            return ok(userService.addRoleToUser(userDtoIu));
        } catch (BaseException e) {
            return error(e.getMessage());
        }
    }
}
