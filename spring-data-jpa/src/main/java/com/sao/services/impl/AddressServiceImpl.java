package com.sao.services.impl;

import com.sao.dto.AddressDto;
import com.sao.dto.CustomerDto;
import com.sao.entities.Address;
import com.sao.repository.AddressRepository;
import com.sao.services.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description:
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressDto findAddressById(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        AddressDto addressDto = new AddressDto();
        Address address = optional.get();
        BeanUtils.copyProperties(address, addressDto);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(address.getCustomer().getId());
        customerDto.setName(address.getCustomer().getName());
//        customerDto.setAddress(addressDto); // Sonsuz döngüye giriyor.

        addressDto.setCustomer(customerDto);
        return addressDto;
    }
}
