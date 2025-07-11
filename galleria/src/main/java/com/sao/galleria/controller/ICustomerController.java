package com.sao.galleria.controller;

import com.sao.galleria.dto.CustomerDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.CustomerDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public interface ICustomerController {

    RootEntity<CustomerDto> saveCustomer(CustomerDtoIU customerDto);
}
