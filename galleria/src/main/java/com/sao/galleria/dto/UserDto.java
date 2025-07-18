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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private String username;

    private String password;
}
