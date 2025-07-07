package com.sao.galleria.service.impl;

import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.service.IAddressService;
import org.springframework.stereotype.Service;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@Service
public class AddressServiceImpl implements IAddressService {

    public void test() {
        // Test method implementation
        throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION,
                "This is a test exception from AddressServiceImpl"));
        }
}
