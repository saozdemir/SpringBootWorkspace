package com.sao.galleria.exception;

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
    CURRENCY_SERVICE_NOT_AVAILABLE("1006", "Currency service is not available at the moment. Please try again later."),
    CUSTOMER_AMOUNT_NOT_ENOUGH("1007", "Customer's account does not have enough balance to complete the transaction."),
    CAR_STATUS_ALREADY_SOLD("1008", "The car is already sold."),
    GENERAL_EXCEPTION("9999", "An unexpected error occurred. Please try again later.");


    private final String code;
    private final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
