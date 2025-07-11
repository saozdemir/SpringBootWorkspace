package com.sao.galleria.mapper;

import com.sao.galleria.dto.AccountDto;
import com.sao.galleria.model.Account;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseMapper<Account, AccountDto> {

    @Override
    AccountDto toDto(Account entity);

    @Override
    Account toEntity(AccountDto dto);
}
