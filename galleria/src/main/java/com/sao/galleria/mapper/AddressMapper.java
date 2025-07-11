package com.sao.galleria.mapper;

import com.sao.galleria.dto.AddressDto;
import com.sao.galleria.model.Address;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressDto> {

    @Override
    AddressDto toDto(Address entity);

    @Override
    Address toEntity(AddressDto dto);
}
