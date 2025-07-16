package com.sao.usermanagement.service.impl;

import com.sao.usermanagement.dto.UserDto;
import com.sao.usermanagement.entity.User;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.exception.ErrorMessage;
import com.sao.usermanagement.exception.MessageType;
import com.sao.usermanagement.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Override
    public UserDto findUserById(Long id) throws BaseException {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            return userMapper.toDto(optionalUser.orElse(null));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,e.getMessage()));
        }
    }
}
