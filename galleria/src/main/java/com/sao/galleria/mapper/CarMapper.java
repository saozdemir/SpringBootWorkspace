package com.sao.galleria.mapper;

import com.sao.galleria.dto.CarDto;
import com.sao.galleria.model.Car;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Jul 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface CarMapper extends BaseMapper<Car, CarDto> {
    @Override
    CarDto toDto(Car entity);

    @Override
    Car toEntity(CarDto dto);
}
