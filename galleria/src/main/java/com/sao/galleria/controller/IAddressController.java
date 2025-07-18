package com.sao.galleria.controller;

import com.sao.galleria.dto.AddressDto;
import com.sao.galleria.dto.RootEntity;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
public interface IAddressController {
    RootEntity<AddressDto> saveAddress(AddressDto addressDto);
}
