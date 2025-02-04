package com.sao.exception.handler;

import com.sao.exception.BaseException;
import com.sao.model.RootEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description: Bu sınıf ile uygulamada meydana gelebilecek hataların yönetimi yapılmaktadır.
 * Bir exception handler olduğunu spirng container'a belirtmek için @ControllerAdvice anotasyonu ile işaretlenir
 * @ExceptionHandler(value = {BaseException.class}) anotasyonu ile value değeri olark atanmış exception türüne karşı yapılacak adımlar tanımlanır.
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Standart hata mesajı üretmek için oluşturulan ApiError türünde ResponseEntitiy döner.
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError> handleBaseException(BaseException exception, WebRequest request) {
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(), request));
    }

    /**
     * Client'a standart tek tip hata mesajı dönmek çin oluşturuldu.
     * Dönecek tüm hata mesajları Bir Exception sınıfına sahip ApiError nesnesi olarak oluşturuluyor.
     * Bu metod hatanın türüne bakmaksızın tüm hata mesajları için kullanıma olanak sağlayan generic bir message alanına sahiptir.
     * Her bir exception için yeni createApiError oluşturmaktan kurtulmuş olduk.
     * @param message
     * @param request
     * @return
     * @param <E>
     */
    public <E> ApiError<E> createApiError(E message, WebRequest request) {
        Exception<E> exception = new Exception<>();
        exception.setCreateTime(new Date());
        exception.setHostName(getHostName());
        exception.setPath(request.getDescription(false).substring(4));/** Başına "uri=" eklemeden sadece path yazdır.*/
        exception.setMessage(message);

        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setException(exception);
        return apiError;
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
