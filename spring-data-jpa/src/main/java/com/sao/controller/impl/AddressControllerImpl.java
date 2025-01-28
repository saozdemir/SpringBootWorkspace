package com.sao.controller.impl;

import com.sao.controller.IAddressController;
import com.sao.dto.AddressDto;
import com.sao.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description: Bu contoroller ile çif taraflı ilişki kullanımı gösterildi
 */
@RestController
@RequestMapping("/rest/api/address")
public class AddressControllerImpl implements IAddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping(path = "/list/{id}")
    @Override
    public AddressDto findAddressById(@PathVariable(name = "id") Long id) {
        return addressService.findAddressById(id);
    }
}
