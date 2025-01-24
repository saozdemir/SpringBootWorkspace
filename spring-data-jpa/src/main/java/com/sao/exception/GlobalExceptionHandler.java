package com.sao.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

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
     *
     * @param ex : Parametre olarak handle edilecek exception verilir.
     * @return ResponseEntity<ApiError> ile bir badRequset oluşturur.
     * Standart hale getirdiğimiz ApiError nesnesinne errorsMap ekleyerek body olarak bunu döndürür.
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            //field ismini alma Ör. (name) ya da (suname) gibi DTO da tamınlanan alanlar.
            String fieldName = ((FieldError) objectError).getField();
            /** Bir field name'e sahip hata varsa mevcut listeye ekle yoksa yeni liste oluştur.*/
            if (errorsMap.containsKey(fieldName)) {
                errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), objectError.getDefaultMessage()));
            } else {
                errorsMap.put(fieldName, addMapValue(new ArrayList<>(), objectError.getDefaultMessage()));
            }
        }

        /** Bir ResponseEntity badRequest oluştutu. Body sine de oluşturduğumuz ApiError içeriğini döner*/
        return ResponseEntity.badRequest().body(createApiError(errorsMap, getStackTraceAsString(ex)));

        /* Generic yapı olduğu için String de gönderebiliriz*/
//        return ResponseEntity.badRequest().body(createApiError("Girilen ID Bulunamadı", getStackTraceAsString(ex)));
    }

    private List<String> addMapValue(List<String> list, String newValue) {
        list.add(newValue);
        return list;
    }

    /**
     * Standart bir ApiError response u oluşturuldu.
     * İlk <T> fonksiyonun generic olduğunu söyler
     * İkinci <T> T tipinde ApiError dönüyor demek
     * Üçüncü T ise parametre olarak T tipinde bir değişken al demek
     * @param errors
     * @return
     */
    private <T> ApiError<T> createApiError(T errors, String stacktrace) {//Map<String, List<String>>
        ApiError<T> apiError = new ApiError<T>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new Date());
        apiError.setErrors(errors);
        apiError.setStacktrace(stacktrace);
        return apiError;
    }

    /**
     * Exception'dan stacktrace'i anlamlı bir String formatında döndürür.
     *
     * @param throwable
     * @return
     */
    private String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
