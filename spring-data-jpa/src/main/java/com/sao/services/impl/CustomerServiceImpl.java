package com.sao.services.impl;

import com.sao.dto.AddressDto;
import com.sao.dto.AddressDtoIU;
import com.sao.dto.CustomerDto;
import com.sao.dto.CustomerDtoIU;
import com.sao.entities.Address;
import com.sao.entities.Customer;
import com.sao.repository.CustomerRepository;
import com.sao.services.ICustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description:
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        if (!customers.isEmpty()) {
            for (Customer customer : customers) {
                CustomerDto customerDto = new CustomerDto();
                AddressDto addressDto = new AddressDto();
                BeanUtils.copyProperties(customer.getAddress(), addressDto);
                BeanUtils.copyProperties(customer, customerDto);
                customerDto.setAddress(addressDto);
                customerDtoList.add(customerDto);
            }
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Customer customer = optional.get();
        Address address = optional.get().getAddress();

        CustomerDto customerDto = new CustomerDto();
        AddressDto addressDto = new AddressDto();

        BeanUtils.copyProperties(address, addressDto);
        BeanUtils.copyProperties(customer, customerDto);
        customerDto.setAddress(addressDto);

        return customerDto;
    }

    @Override
    @Transactional
    public CustomerDto saveCustomer(CustomerDtoIU saveCustomer) {
        // Get DtoUI and prepare Entity
        AddressDtoIU addressDtoIU = new AddressDtoIU();
        addressDtoIU = saveCustomer.getAddress();

        Address address = new Address();
        BeanUtils.copyProperties(addressDtoIU, address);

        Customer customer = new Customer();
        BeanUtils.copyProperties(saveCustomer, customer);
        customer.setAddress(address);
        // Save
        customer = customerRepository.save(customer);

        //Create Response
        AddressDto addressDto = new AddressDto();
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer.getAddress(), addressDto);
        BeanUtils.copyProperties(customer, customerDto);
        customerDto.setAddress(addressDto);
        return customerDto;
    }

    @Override
    @Transactional
    public CustomerDto deleteCustomer(Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        CustomerDto customerDto = new CustomerDto();
        AddressDto addressDto = new AddressDto();
        if (optional.isPresent()) {
            customerRepository.delete(optional.get());
            BeanUtils.copyProperties(optional.get().getAddress(), addressDto);
            BeanUtils.copyProperties(optional.get(), customerDto);
            customerDto.setAddress(addressDto);
            return customerDto;
        }
        return null;
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(CustomerDtoIU updateCustomer, Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = new Customer();
            Address address = new Address();

            BeanUtils.copyProperties(updateCustomer.getAddress(), address);
            address.setId(optional.get().getAddress().getId());
            BeanUtils.copyProperties(updateCustomer, customer);
            customer.setAddress(address);
            customer.setId(optional.get().getId());

            Customer responseCustomer = customerRepository.save(customer);

            CustomerDto responseCustomerDto = new CustomerDto();
            AddressDto responseAddressDto = new AddressDto();

            BeanUtils.copyProperties(responseCustomer.getAddress(), responseAddressDto);
            BeanUtils.copyProperties(responseCustomer, responseCustomerDto);
            responseCustomerDto.setAddress(responseAddressDto);

            return responseCustomerDto;
        }
        return null;
    }
}
