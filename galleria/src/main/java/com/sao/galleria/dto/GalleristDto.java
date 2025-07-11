package com.sao.galleria.dto;

import com.sao.galleria.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class GalleristDto extends BaseDto{

    private String firstName;

    private String lastName;

    private AddressDto address;
}
