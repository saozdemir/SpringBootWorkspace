package com.sao.galleria.service;

import com.sao.galleria.dto.AddressDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
public interface IAddressService {

    AddressDto saveAddress(AddressDto addressDto);
}
