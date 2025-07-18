package com.sao.usermanagement.controller;

import com.sao.usermanagement.dto.RootEntity;
import com.sao.usermanagement.dto.UserDto;
import com.sao.usermanagement.dto.iu.UserDtoIU;
import com.sao.usermanagement.exception.BaseException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
public interface IUserController {

    RootEntity<UserDto> findUserById(Long id);

    RootEntity<UserDto> addRoleToUser(UserDtoIU userDtoIu);

}
