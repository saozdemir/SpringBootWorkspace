package com.sao.galleria.service.impl;

import com.sao.galleria.dto.AuthRequest;
import com.sao.galleria.dto.AuthResponse;
import com.sao.galleria.dto.RefreshTokenRequest;
import com.sao.galleria.dto.UserDto;
import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.jwt.JwtService;
import com.sao.galleria.mapper.UserMapper;
import com.sao.galleria.model.RefreshToken;
import com.sao.galleria.model.User;
import com.sao.galleria.repository.RefreshTokenRepository;
import com.sao.galleria.repository.UserRepository;
import com.sao.galleria.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
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

    private User createUser(AuthRequest authRequest) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(authRequest.username());
        user.setPassword(passwordEncoder.encode(authRequest.password())); // Şifreyi şifrele çünkü düz metin olarak karşılaştırılmaz
        return user;
    }


    @Override
    public UserDto register(AuthRequest authRequest) {
        User user = createUser(authRequest);
        userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto = userMapper.toDto(user); // User entity'sini UserDto'ya dönüştür
//        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            /** Kullanıcı adı ve şifre ile kimlik doğrulama işlemi yapılır. */
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
            authenticationProvider.authenticate(authenticationToken);

            /** Kimlik doğrulama başarılıysa, kullanıcı bilgileri alınır. */
            Optional<User> optional = userRepository.findByUsername(authRequest.username());

            String accessToken = jwtService.generateToken(optional.get());
            RefreshToken refreshToken = jwtService.generateRefreshToken(optional.get());
            refreshTokenRepository.save(refreshToken);
            return new AuthResponse(accessToken, refreshToken.getRefreshToken());
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INCORRECT, e.getMessage()));
        }
    }

    /**
     * Kullanıcının refresh token geçerlimi veritabında kontrol edilir.
     * Token geçerli değil ise hata mesajı fırlatılır.
     *
     * @param refreshTokenRequest
     * @return authResponse
     * @throws : REFRESH_TOKEN_NOT_FOUND
     *           Eğer geçerli ise expired durumu kontrol edilir. Expired ise hata mesajı fırlatılır.
     * @throws : REFRESH_TOKEN_IS_EXPIRED
     */
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
