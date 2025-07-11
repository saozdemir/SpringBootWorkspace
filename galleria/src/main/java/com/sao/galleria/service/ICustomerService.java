package com.sao.galleria.service;

import com.sao.galleria.dto.CustomerDto;
import com.sao.galleria.dto.iu.CustomerDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public interface ICustomerService {

    CustomerDto saveCustomer(CustomerDtoIU customerDtoIu);
}
