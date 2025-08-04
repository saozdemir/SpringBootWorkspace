package com.sao.usermanagement.service.impl;

import com.sao.usermanagement.dto.UserDto;
import com.sao.usermanagement.dto.iu.UserDtoIU;
import com.sao.usermanagement.entity.Role;
import com.sao.usermanagement.entity.User;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.exception.ErrorMessage;
import com.sao.usermanagement.exception.MessageType;
import com.sao.usermanagement.mapper.UserMapper;
import com.sao.usermanagement.repository.RoleRepository;
import com.sao.usermanagement.repository.UserRepository;
import com.sao.usermanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findUserById(Long id) throws BaseException {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            return userMapper.toDto(optionalUser.orElse(null));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, e.getMessage()));
        }
    }

    @Override
    public UserDto addRoleToUser(UserDtoIU userDtoIU) throws BaseException {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(userDtoIU.getUsername());
            if (optionalUser.isPresent() && !userDtoIU.getRoleIds().isEmpty()) {
                User user = optionalUser.get();
                for (Long roleId : userDtoIU.getRoleIds()) {
                    Optional<Role> optionalRole = roleRepository.findById(roleId);
                    if (optionalRole.isPresent()) {
                        Role role = optionalRole.get();
                        user.getRoles().add(role);
                    } else {
                        throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Role: " + roleId));
                    }
                }
                User updatedUser = userRepository.save(user);
                return userMapper.toDto(updatedUser);
            } else {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User: " + userDtoIU.getUsername()));
            }
        } catch (BaseException e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
    }

    @Override
    public UserDto testUserAuthentication(UserDtoIU userDtoIu) throws BaseException {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(userDtoIu.getUsername());
            return userMapper.toDto(optionalUser.orElse(null));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
    }
}