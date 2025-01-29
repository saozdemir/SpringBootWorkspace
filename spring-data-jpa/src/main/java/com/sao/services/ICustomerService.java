package com.sao.services;

import com.sao.dto.CustomerDto;

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
