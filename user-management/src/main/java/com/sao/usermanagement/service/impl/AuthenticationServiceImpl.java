package com.sao.usermanagement.service.impl;

import com.sao.usermanagement.dto.AuthRequest;
import com.sao.usermanagement.dto.AuthResponse;
import com.sao.usermanagement.dto.RefreshTokenRequest;
import com.sao.usermanagement.dto.UserDto;
import com.sao.usermanagement.dto.iu.UserDtoIU;
import com.sao.usermanagement.entity.RefreshToken;
import com.sao.usermanagement.entity.User;
import com.sao.usermanagement.exception.BaseException;
import com.sao.usermanagement.exception.ErrorMessage;
import com.sao.usermanagement.exception.MessageType;
import com.sao.usermanagement.mapper.UserMapper;
import com.sao.usermanagement.repository.RefreshTokenRepository;
import com.sao.usermanagement.repository.UserRepository;
import com.sao.usermanagement.security.jwt.JwtService;
import com.sao.usermanagement.service.IAuthenticationService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserMapper userMapper;

    private User createUser(UserDtoIU userDtoIu) {
        User user = userMapper.toEntityFromDtoIu(userDtoIu);
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(userDtoIu.getPassword()));
        return user;
    }


    @Override
    public UserDto register(UserDtoIU userDtoIu) {
        User user = createUser(userDtoIu);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optionalUser = userRepository.findByUsername(authRequest.username());
            optionalUser.get().setSelectedRole(authRequest.selectedRole());

            String accessToken = jwtService.generateToken(optionalUser.get());
            RefreshToken refreshToken = jwtService.generateRefreshToken(optionalUser.get());
            refreshTokenRepository.save(refreshToken);
            if(authRequest.selectedRole() != null) {
                userRepository.save(optionalUser.get()); /** Kullanıcıyı seçilen role ile güncelle. */
            }
            return new AuthResponse(accessToken, refreshToken.getRefreshToken());
        } catch (AuthenticationException e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INCORRECT, e.getMessage()));
        }
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.refreshToken());
        if (optionalRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshTokenRequest.refreshToken()));
        }

        if (!jwtService.isRefreshTokenValid(optionalRefreshToken.get().getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, refreshTokenRequest.refreshToken()));
        }

        /** Eğer refresh token geçerli ise yeni accessToken ve refreshTonek oluşturur. */
        User user = optionalRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = jwtService.generateRefreshToken(user);
        refreshTokenRepository.save(refreshToken);
        return new AuthResponse(accessToken, refreshToken.getRefreshToken());
    }
}
