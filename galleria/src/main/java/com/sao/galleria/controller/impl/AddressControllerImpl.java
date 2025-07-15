package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.IAddressController;
import com.sao.galleria.dto.AddressDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "api/galleria/address")
public class AddressControllerImpl extends BaseController implements IAddressController {
    @Autowired
    private IAddressService addressService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<AddressDto> saveAddress(@Valid @RequestBody AddressDto addressDto) {
        try {
            return ok(addressService.saveAddress(addressDto));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
