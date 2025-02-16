package com.sao.service.impl;

import com.sao.jwt.AuthResponse;
import com.sao.jwt.JwtService;
import com.sao.jwt.RefreshTokenRequest;
import com.sao.model.entity.RefreshToken;
import com.sao.model.entity.User;
import com.sao.repository.RefreshTokenRepository;
import com.sao.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Şub 2025
 * <p>
 * @description:
 */
@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 *2)); /** 4 saat geçerliliği var: 1000 * 60 * 60 * 4*/
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public void saveRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    public boolean isRefreshTokenExpired(Date expiredDate) {
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse getRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken());

        /** Refresh token veritabanında var mı kontrol et*/
        if(optional.isEmpty()) {
            System.out.println("REFRESH TOKEN GEÇERSİZ : " + refreshTokenRequest.getRefreshToken());
        }
        RefreshToken refreshToken = optional.get();

        /** Refresh token expire oldu mu?*/
        if(!isRefreshTokenExpired(refreshToken.getExpireDate())) {
            System.out.println("REFRESH TOKEN EXPİRE OLMUŞTUR : " + refreshToken.getRefreshToken());
        }

        /** Yeni access token üret*/
        String accessToken = jwtService.generateToken(refreshToken.getUser());

        /** Yeni refresh token üret ve kaydet*/
        RefreshToken newRefreshToken = createRefreshToken(refreshToken.getUser());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(newRefreshToken);

        /** Kullanıcıya yeni AuthResponse dön. Bu response içinde access ve refresh token olacak.*/
        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }

    @Override
    public void deleteExpiredRefreshToken() {
        List<RefreshToken> refreshTokens = refreshTokenRepository.findAll();
        if(!refreshTokens.isEmpty()) {
            for (RefreshToken refreshToken : refreshTokens) {
                if(refreshToken.getExpireDate().before(new Date())) {
                    refreshTokenRepository.deleteById(refreshToken.getId());
                }
            }
        }
    }
}
