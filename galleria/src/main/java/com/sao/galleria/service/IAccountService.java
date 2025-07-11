package com.sao.galleria.service;

import com.sao.galleria.dto.AccountDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public interface IAccountService {
    AccountDto saveAccount(AccountDto accountDto);
}
