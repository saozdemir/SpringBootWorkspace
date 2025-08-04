package com.sao.usermanagement.service;

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
public interface IUserService {

    UserDto findUserById (Long id) throws BaseException;

    UserDto addRoleToUser(UserDtoIU userDtoIU) throws BaseException;

    UserDto testUserAuthentication(UserDtoIU userDtoIu) throws BaseException;
}
