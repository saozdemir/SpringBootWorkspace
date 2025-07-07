package com.sao.galleria.mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
public interface BaseMapper <E, D>{
    /**
     * Bu metot, D tipindeki DTO nesnesini E tipindeki entity nesnesine dönüştürür.
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     * Bu metot, D tipindeki DTO nesnesini E tipindeki entity nesnesine dönüştürür.
     * @param dto
     * @return
     */
    E toEntity(D dto);
}
