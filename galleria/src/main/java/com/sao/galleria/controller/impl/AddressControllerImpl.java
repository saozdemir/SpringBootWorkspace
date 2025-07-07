package com.sao.galleria.controller.impl;

import com.sao.galleria.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "api/galleria/address")
public class AddressControllerImpl {
    @Autowired
    private IAddressService addressService;

    @GetMapping(path = "/test")
    public void test() {
        // Test method implementation
        addressService.test();
    }
}
