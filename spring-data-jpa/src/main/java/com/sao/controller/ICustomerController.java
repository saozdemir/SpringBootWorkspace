package com.sao.controller;

import com.sao.dto.CustomerDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 Oca 2025
 * <p>
 * @description:
 */
public interface ICustomerController {
    CustomerDto findCustomerById(Long id);
}
