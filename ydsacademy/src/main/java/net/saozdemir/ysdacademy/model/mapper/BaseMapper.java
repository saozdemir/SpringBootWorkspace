package net.saozdemir.ysdacademy.model.mapper;

import java.io.Serializable;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 08 Mar 2026
 * <p>
 * @description: Tüm Mapper sınıflarının ortak özelliklerini barındıran sınıf.
 * Bu sınıf, DTO ve Entity dönüşümlerini yönetmek için oluşturulmuştur.
 */
public interface BaseMapper<D, E> extends Serializable {
    D toDto(E entity);

    E toEntity(D dto);
}
