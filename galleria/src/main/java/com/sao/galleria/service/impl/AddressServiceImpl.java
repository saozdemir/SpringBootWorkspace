package com.sao.galleria.service.impl;

import com.sao.galleria.dto.AddressDto;
import com.sao.galleria.mapper.AddressMapper;
import com.sao.galleria.model.Address;
import com.sao.galleria.repository.AddressRepository;
import com.sao.galleria.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    private Address createAddressFromDto(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        address.setCreateTime(new Date());
        return address;
    }

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        Address savedAddress = addressRepository.save(createAddressFromDto(addressDto));
        return addressMapper.toDto(savedAddress);
    }
}
