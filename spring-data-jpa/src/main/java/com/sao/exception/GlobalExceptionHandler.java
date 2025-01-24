package com.sao.exception;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 24 Oca 2025
 * <p>
 * @description: Fırlatılan exceptionları global seviyede yakalamak için kullanılır.
 * Bu adımda Spring Validationdan alınan hataları yönetip anlamlı response dönmek.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Fırlatılan exception türüne göre yapılacaklar tanımlanır.
     * @param ex : Parametre olarak handle edilecek exception verilir.
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        for(ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            //field ismini alma Ör. (name) ya da (suname) gibi DTO da tamınlanan alanlar.
            String fieldName = ((FieldError)objectError).getField();
            if(errorsMap.containsKey(fieldName)){
                errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), objectError.getDefaultMessage()));
            }else {
                errorsMap.put(fieldName, addMapValue(new ArrayList<>(), objectError.getDefaultMessage()));
            }
        }
    }

    private List<String> addMapValue(List<String> list, String newValue){
        list.add(newValue);
        return list;
    }
}
