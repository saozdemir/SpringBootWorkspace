package com.sao.controller;

import com.sao.dto.CustomerDto;
import com.sao.dto.CustomerDtoIU;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 Oca 2025
 * <p>
 * @description:
 */
public interface ICustomerController {

    List<CustomerDto> getAllCustomer();

    CustomerDto findCustomerById(Long id);

    CustomerDto saveCustomer(CustomerDtoIU saveCustomer);

    CustomerDto deleteCustomer(Long id);

    CustomerDto updateCustomer(CustomerDtoIU updateCustomer, Long id);
}
