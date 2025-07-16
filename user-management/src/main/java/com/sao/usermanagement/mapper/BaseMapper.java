package com.sao.usermanagement.mapper;

import com.sao.usermanagement.dto.iu.UserDtoIU;
import com.sao.usermanagement.entity.User;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
public interface BaseMapper <E, D, U> {

    /**
     * Converts an entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    D toDto(E entity);

    /**
     * Converts a DTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    E toEntity(D dto);

    /**
     * Converts a DTO for insert update operations to an entity.
     *
     * @param dtoIu the DTO for update operations
     * @return the converted entity
     */
    E toEntityFromDtoIu(U dtoIu);
}
