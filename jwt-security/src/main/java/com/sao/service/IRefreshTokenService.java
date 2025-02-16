package com.sao.service;

import com.sao.jwt.AuthResponse;
import com.sao.jwt.RefreshTokenRequest;
import com.sao.model.entity.RefreshToken;
import com.sao.model.entity.User;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Şub 2025
 * <p>
 * @description:
 */
public interface IRefreshTokenService {
    RefreshToken createRefreshToken(User user);

    void saveRefreshToken(RefreshToken refreshToken);

    AuthResponse getRefreshToken(RefreshTokenRequest refreshTokenRequest);

    void deleteExpiredRefreshToken();
}
