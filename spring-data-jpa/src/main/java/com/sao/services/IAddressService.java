package com.sao.services;

import com.sao.dto.AddressDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description:
 */
public interface IAddressService {

    AddressDto findAddressById(Long id);
}
