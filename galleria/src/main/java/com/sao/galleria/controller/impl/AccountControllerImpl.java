package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.IAccountController;
import com.sao.galleria.dto.AccountDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.service.IAccountService;
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
@RequestMapping(path = "api/galleria/account")
public class AccountControllerImpl extends BaseController implements IAccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<AccountDto> saveAccount(@Valid @RequestBody AccountDto accountDto) {
        return ok(accountService.saveAccount(accountDto));
    }
}
