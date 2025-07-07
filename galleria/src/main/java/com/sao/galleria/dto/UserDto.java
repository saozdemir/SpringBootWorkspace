package com.sao.galleria.dto;

import lombok.Data;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
@Data
public class UserDto extends BaseDto {
    private String username;

    private String password;
}
