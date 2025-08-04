package com.sao.usermanagement.exception.handler;

import com.sao.usermanagement.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {java.lang.Exception.class})
    public ResponseEntity<?> handleException(java.lang.Exception exception, WebRequest request) {
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(), request));
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<?> handleBaseException(BaseException exception, WebRequest request) {
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(), request));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        Map<String, List<String>> map = new HashMap<>();
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) objectError).getField();
            if (map.containsKey(fieldName)) {
                map.put(fieldName, addValue(map.get(fieldName), objectError.getDefaultMessage()));
            } else {
                map.put(fieldName, addValue(new ArrayList<>(), objectError.getDefaultMessage()));
            }
        }


        return ResponseEntity.badRequest().body(createApiError(map, request));
    }

    private List<String> addValue(List<String> list, String value) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(value);
        return list;
    }

    private String getHostName() {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "Unknown Host";
    }

    public <E> ApiError<E> createApiError(E message, WebRequest request) {
        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false).substring(4));/** Başına "uri=" eklemeden sadece path yazdır.*/
        exception.setCreateTime(new Date());
        exception.setMessage(message);
        exception.setHostName(getHostName());


        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setException(exception);
        return apiError;
    }
}
