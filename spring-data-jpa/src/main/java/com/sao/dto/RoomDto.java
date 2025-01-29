package com.sao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Oca 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private String name;
}
