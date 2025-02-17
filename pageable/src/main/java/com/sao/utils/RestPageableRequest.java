package com.sao.utils;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 18 Şub 2025
 * <p>
 * @description:
 */

import lombok.Data;

@Data
public class RestPageableRequest {
    private int pageNumber;
    private int pageSize;
    private String columnName;
    private boolean asc;
}
