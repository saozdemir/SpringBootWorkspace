package com.sao.galleria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public record AuthResponse (String accessToken, String refreshToken){
//    private String accessToken;
//
//    private String refreshToken;
}
