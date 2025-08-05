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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    public AuthResponse authenticate(AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
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

            /** Cookie ekleme adımı (YENİ)*/
            addRefreshTokenToCookie(refreshToken, response);

            // YENİ: CSRF Token'ını Request'ten alıp response'a ekleme
            // Spring Security, CSRF token'ını ürettikten sonra request'e bir attribute olarak ekler.
            String csrfToken = generateCsrfToken(request, response);

            return new AuthResponse(accessToken);
        } catch (AuthenticationException e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INCORRECT, e.getMessage()));
        }
    }

    @Override
    public AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshTokenFromCookie = getRefreshTokenFromCookie(request);
        if (refreshTokenFromCookie == null) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, "Refresh token not found in cookie"));
        }

        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenFromCookie);
        if (optionalRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshTokenFromCookie));
        }

        if (!jwtService.isRefreshTokenValid(optionalRefreshToken.get().getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, refreshTokenFromCookie));
        }

        User user = optionalRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken newRefreshToken = jwtService.generateRefreshToken(user);
        refreshTokenRepository.save(newRefreshToken);

        // YENİ EKLENEN KISIM: Yeni RefreshToken'ı HttpOnly Cookie'ye ekleme
        addRefreshTokenToCookie(newRefreshToken, response);

        // Sadece yeni accessToken'ı body'de döndür.
        return new AuthResponse(accessToken);
    }

    private void addRefreshTokenToCookie(RefreshToken refreshToken, HttpServletResponse response) {
        Cookie cookie = new Cookie("refresh-token", refreshToken.getRefreshToken());
        cookie.setHttpOnly(true); // JavaScript erişimini engeller
//TODO:        cookie.setSecure(true); // Sadece HTTPS üzerinden gönderilir (Production için önemlidir)
        cookie.setPath("/"); // Tüm path'lerde geçerli
        long duration = refreshToken.getExpiredDate().getTime() - System.currentTimeMillis();
        cookie.setMaxAge((int) TimeUnit.MILLISECONDS.toSeconds(duration)); // Cookie'nin ömrünü ayarla
        response.addCookie(cookie);
    }

    // Cookie'den refresh token okumak için yardımcı metod
    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh-token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private String generateCsrfToken(HttpServletRequest request, HttpServletResponse response) {
        String csrfToken = null;
        try {
            CsrfToken csrfTokenData = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

            if (csrfTokenData != null) {
                csrfToken =  csrfTokenData.getToken();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CSRF_TOKEN_NOT_FOUND, e.getMessage()));
        }
        return csrfToken;
    }
}
