package com.sao.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description: Bu sınıfta kullnaıcıya token dönerken hem access token hem de oturum yenilemede kullanılacak
 * refresh token gönderildi.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;

    private String refreshToken;
}
