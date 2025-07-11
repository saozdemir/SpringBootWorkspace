package com.sao.galleria.service.impl;

import com.sao.galleria.dto.CustomerDto;
import com.sao.galleria.dto.iu.CustomerDtoIU;
import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.mapper.CustomerMapper;
import com.sao.galleria.model.Account;
import com.sao.galleria.model.Address;
import com.sao.galleria.model.Customer;
import com.sao.galleria.repository.AccountRepository;
import com.sao.galleria.repository.AddressRepository;
import com.sao.galleria.repository.CustomerRepository;
import com.sao.galleria.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerMapper customerMapper;

    private Customer createCustomerFromDto(CustomerDtoIU customerDtoIu) {
        Optional<Address> addressOptional = addressRepository.findById(customerDtoIu.getAddressId());
        if (addressOptional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, customerDtoIu.getAddressId().toString()));
        }

        Optional<Account> accountOptional = accountRepository.findById(customerDtoIu.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, customerDtoIu.getAccountId().toString()));
        }
        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(customerDtoIu, customer);
        customer.setAddress(addressOptional.get());
        customer.setAccount(accountOptional.get());
        return customer;
    }

    @Override
    public CustomerDto saveCustomer(CustomerDtoIU customerDtoIu) {
        Customer savedCustomer = customerRepository.save(createCustomerFromDto(customerDtoIu));
        return customerMapper.toDto(savedCustomer);
    }
}
