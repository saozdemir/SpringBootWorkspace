package com.sao.exception.handler;

import lombok.Data;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description: Bu sınıf client'a bir hata oluşması durumunda standart bir geri dönüş üretmek için kullanılıyor.
 * HTTP status ve hangi exception alınmışsa onun detaylarını içeren bir Exception nesnesine sahiptir.
 */
@Data
public class ApiError <E>{
    private Integer status;

    private Exception<E> exception;
}
