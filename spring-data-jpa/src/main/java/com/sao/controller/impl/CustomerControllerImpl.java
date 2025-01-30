package com.sao.controller.impl;

import com.sao.controller.ICustomerController;
import com.sao.dto.CustomerDto;
import com.sao.dto.CustomerDtoIU;
import com.sao.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 Oca 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/rest/api/customer")
public class CustomerControllerImpl implements ICustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping(path = "/list")
    @Override
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public CustomerDto findCustomerById(@PathVariable(name = "id") Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping(path = "/save")
    @Override
    public CustomerDto saveCustomer(@RequestBody CustomerDtoIU saveCustomer) {
        return customerService.saveCustomer(saveCustomer);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public CustomerDto deleteCustomer(@PathVariable(name = "id") Long id) {
        return customerService.deleteCustomer(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public CustomerDto updateCustomer(@RequestBody CustomerDtoIU updateCustomer, @PathVariable(name = "id") Long id) {
        return customerService.updateCustomer(updateCustomer, id);
    }
}
