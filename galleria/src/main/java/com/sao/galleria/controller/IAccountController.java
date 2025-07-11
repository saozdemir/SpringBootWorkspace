package com.sao.galleria.controller;

import com.sao.galleria.dto.AccountDto;
import com.sao.galleria.dto.RootEntity;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public interface IAccountController {
    RootEntity<AccountDto> saveAccount(AccountDto accountDto);
}
