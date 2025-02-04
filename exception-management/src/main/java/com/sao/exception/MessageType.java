package com.sao.exception;

import lombok.Getter;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description:
 */
@Getter
public enum MessageType {
    NO_RECORD_EXIST("1001", "Kayıt Bulunamadı"),
    UNHANDLED_EXCEPTION("9999", "Ele alınamayan hata oluştu");


    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
