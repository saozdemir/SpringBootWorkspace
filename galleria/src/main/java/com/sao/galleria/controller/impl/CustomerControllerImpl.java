package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.ICustomerController;
import com.sao.galleria.dto.CustomerDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.CustomerDtoIU;
import com.sao.galleria.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/galleria/customer")
public class CustomerControllerImpl extends BaseController implements ICustomerController {

    @Autowired
    private ICustomerService customerService;


    @PostMapping(path = "/save")
    @Override
    public RootEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDtoIU customerDtoIu) {
        try {
            return ok(customerService.saveCustomer(customerDtoIu));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
