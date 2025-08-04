package com.sao.usermanagement.exception;

import lombok.Getter;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@Getter
public enum MessageType {
    NO_RECORD_EXIST("1000", "No record exists for the given criteria."),
    TOKEN_IS_EXPIRED("1001", "The token has expired. Please log in again."),
    USERNAME_NOT_FOUND("1002", "Username not found."),
    USERNAME_OR_PASSWORD_INCORRECT("1003", "Username or password is incorrect."),
    REFRESH_TOKEN_NOT_FOUND("1004", "Refresh token not found."),
    REFRESH_TOKEN_IS_EXPIRED("1005", "The refresh token has expired. Please log in again."),
    CSRF_TOKEN_NOT_FOUND("1006", "CSRF token not found."),

    GENERAL_EXCEPTION("9999", "An unexpected error occurred. Please try again later.");


    private final String code;
    private final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
