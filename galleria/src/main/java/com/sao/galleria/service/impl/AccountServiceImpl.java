package com.sao.galleria.service.impl;

import com.sao.galleria.dto.AccountDto;
import com.sao.galleria.mapper.AccountMapper;
import com.sao.galleria.model.Account;
import com.sao.galleria.repository.AccountRepository;
import com.sao.galleria.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    private Account createAccountFromDto(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account.setCreateTime(new Date());
        return account;
    }

    @Override
    public AccountDto saveAccount(AccountDto accountDto) {
        Account savedAccount = accountRepository.save(createAccountFromDto(accountDto));
        return accountMapper.toDto(savedAccount);
    }
}
