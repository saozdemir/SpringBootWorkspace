package com.sao.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 25 Oca 2025
 * <p>
 * @description: Hangi tipte hata fırlatılısa bu standartta response üretilecek.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError<T> {
    private String id;
    private Date errorTime;
    private T errors;// Map<String , List<String>>
    private String stacktrace;
}
