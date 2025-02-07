package com.sao.service.impl;

import com.sao.jwt.AuthRequest;
import com.sao.model.dto.UserDto;
import com.sao.model.entity.User;
import com.sao.repository.UserRepository;
import com.sao.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Bu encoderin AppConfig altında bean'i oluşturuldu. Kullanıcının şifresini kriptolamak için kullanılıyor.
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto register(AuthRequest request) {
        UserDto userDto = new UserDto();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));/** Kullanıcıdan alınan password kriptolanıp o şekilde set edildi.*/

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, userDto);

        return userDto;
    }
}
