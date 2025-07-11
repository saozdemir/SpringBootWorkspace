package com.sao.galleria.mapper;

import com.sao.galleria.dto.CustomerDto;
import com.sao.galleria.dto.iu.CustomerDtoIU;
import com.sao.galleria.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto> {

    @Override
    CustomerDto toDto(Customer entity);

    @Override
    Customer toEntity(CustomerDto dto);
}
