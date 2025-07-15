package com.sao.galleria.mapper;

import com.sao.galleria.dto.SoldCarDto;
import com.sao.galleria.model.SoldCar;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface SoldCarMapper extends BaseMapper<SoldCar, SoldCarDto> {
}
