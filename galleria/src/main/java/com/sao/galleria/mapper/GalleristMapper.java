package com.sao.galleria.mapper;

import com.sao.galleria.dto.GalleristDto;
import com.sao.galleria.model.Gallerist;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface GalleristMapper extends BaseMapper<Gallerist, GalleristDto>{

    @Override
    GalleristDto toDto(Gallerist entity);

    @Override
    Gallerist toEntity(GalleristDto dto);
}
