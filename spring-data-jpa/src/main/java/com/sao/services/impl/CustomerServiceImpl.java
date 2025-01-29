package com.sao.services.impl;

import com.sao.dto.AddressDto;
import com.sao.dto.CustomerDto;
import com.sao.entities.Address;
import com.sao.entities.Customer;
import com.sao.repository.CustomerRepository;
import com.sao.services.ICustomerService;
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
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto findCustomerById(Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Customer customer = optional.get();
        Address address = optional.get().getAddress();

        CustomerDto customerDto = new CustomerDto();
        AddressDto addressDto = new AddressDto();

        BeanUtils.copyProperties(address, addressDto);
        BeanUtils.copyProperties(customer, customerDto);
        customerDto.setAddress(addressDto);

        return customerDto;
    }
}
