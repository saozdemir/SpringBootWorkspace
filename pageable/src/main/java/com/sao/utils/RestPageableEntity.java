package com.sao.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 19 Şub 2025
 * <p>
 * @description: Bize özelleştirilmiş response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestPageableEntity<T> {
    private List<T> content;

    private int pageNumber;

    private int pageSize;

    private long totalElements;
}
