package com.sao.exception.handler;

import lombok.Data;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description: ApiError içinde tanımlanıp client'a standart bir JSOn formatında çıktı üretilmek içn kullanılıyor.
 * "message" alanı gelen hatanın türünden bağımsız tüm exceptionlarda kullanılabilir.
 */
@Data
public class Exception<E> {
    private String hostName;

    private String path;

    private Date createTime;

    private E message;
}
