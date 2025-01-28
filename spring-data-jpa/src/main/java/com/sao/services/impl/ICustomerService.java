package com.sao.services.impl;

import com.sao.dto.CustomerDto;
import com.sao.repository.CustomerRepository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description:
 */
public interface ICustomerService {

    CustomerDto findCustomerById(Long id);
}
