package com.sao.controller;

import com.sao.dto.AddressDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description:
 */
public interface IAddressController {
    AddressDto findAddressById(Long id);
}
