package com.sao.galleria.dto;

import com.sao.galleria.model.Account;
import com.sao.galleria.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthOfDate;

    private AddressDto address;

    private AccountDto account;
}
