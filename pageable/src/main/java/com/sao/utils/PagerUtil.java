package com.sao.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 18 Şub 2025
 * <p>
 * @description: @UtilityClass   : Bu anotasyon ile sınıf nesnesi üretilmez ve metotlar statik olur.
 */
@UtilityClass
public class PagerUtil {

    public boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public Pageable toPageable(RestPageableRequest request) {
        /** Sıralama kullanmak istiyor mu?*/
        if (!isNullOrEmpty(request.getColumnName())) {
            Sort sortBy = request.isAsc()
                    ? Sort.by(Sort.Direction.ASC, request.getColumnName())
                    : Sort.by(Sort.Direction.DESC, request.getColumnName());
            return PageRequest.of(request.getPageNumber(), request.getPageSize(), sortBy);
        }
        return PageRequest.of(request.getPageNumber(), request.getPageSize());
    }

    /**
     * Karmaşık bir response yerine sadece istediğimiz alanları dönecek bir response oluşturuldu.
     *
     * @param page
     * @param content
     * @param <T>
     * @return
     */
    public <T> RestPageableEntity<T> toPageableResponse(Page<T> page, List<T> content) {
        RestPageableEntity<T> entity = new RestPageableEntity<T>();
        entity.setContent(content);
        entity.setPageNumber(page.getPageable().getPageNumber());
        entity.setPageSize(page.getPageable().getPageSize());
        entity.setTotalElements(page.getTotalElements());
        return entity;
    }
}
