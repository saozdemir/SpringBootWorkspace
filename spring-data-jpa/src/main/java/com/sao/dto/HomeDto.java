package com.sao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class HomeDto {
    private Long id;
    private BigDecimal price;
    private List<RoomDto> rooms= new ArrayList<>(); // Null Pointer atmaması için
}
