package com.sao.galleria.mapper;

import com.sao.galleria.dto.GalleristCarDto;
import com.sao.galleria.model.GalleristCar;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface GalleristCarMapper extends BaseMapper<GalleristCar, GalleristCarDto> {

    @Override
    GalleristCarDto toDto(GalleristCar entity);

    @Override
    GalleristCar toEntity(GalleristCarDto dto);
}
