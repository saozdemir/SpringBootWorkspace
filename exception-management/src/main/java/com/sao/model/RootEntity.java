package com.sao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 04 Şub 2025
 * <p>
 * @description: Bu sınıf kullanıcıya dönülecek standart bir DTO sınıfı oluşturmak için kullnaılacak.
 * Hata mesajı varsa hata mesajı, yoksa dönmek istenen veri dönecek.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootEntity<T> {
    boolean result;

    private T errorMessage;

    private T data;

    /**
     * Bir generic fonksiyon oluşturduk.
     * Bu sayede gelen datanın tründen bağımsız geri döndürebileceğimiz bir yapı elde ettik.
     * Bu metotta hata alınmadığı senaryo için geri dönüş nesnesi oluşturuldu.
     * Başarılı sonuç için result true yapıldı.
     * Hata mesajı oladığı için null
     * setData ile client'a döneek data set edildi.
     *
     * @param data
     * @param <T>
     * @return
     */
    public static  <T> RootEntity<T> ok(T data) {
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setData(data);
        rootEntity.setResult(true);
        rootEntity.setErrorMessage(null);

        return rootEntity;
    }

    /**
     * Bu metotta hata alınan senaryo için geri dönüş nesnesi oluşturuldu.
     * hata oluştuğu ve bir veri dönülmeyeceği için setData null atandı.
     * hata mesajı oluşacağından setErrorMessage alanına hata mesajı set edildi.
     * hata meydana geldiği için result false oldu.
     *
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static  <T> RootEntity<T> error(T errorMessage) {
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setData(null);
        rootEntity.setErrorMessage(errorMessage);
        rootEntity.setResult(false);

        return rootEntity;
    }
}
