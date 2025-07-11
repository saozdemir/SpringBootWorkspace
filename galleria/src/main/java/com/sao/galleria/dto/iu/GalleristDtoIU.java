package com.sao.galleria.dto.iu;

import com.sao.galleria.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Data
public class GalleristDtoIU {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Long addressId;
}
