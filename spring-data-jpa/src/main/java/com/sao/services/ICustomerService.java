package com.sao.services;

import com.sao.dto.CustomerDto;
import com.sao.dto.CustomerDtoIU;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description:
 */
public interface ICustomerService {

    List<CustomerDto> getAllCustomer();

    CustomerDto findCustomerById(Long id);

    CustomerDto saveCustomer(CustomerDtoIU saveCustomer);

    CustomerDto deleteCustomer(Long id);

    CustomerDto updateCustomer(CustomerDtoIU updateCustomer, Long id);
}
