package com.sao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Oca 2025
 * <p>
 * @description: Bu sınıf client a veri dönerken kullanılacak alanların belirlendiği classtır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) /** Bu sınıf içinde null set edilen alan varsa geri null geri dönme*/
public class CustomerDto {
    private Long id;
    private String name;
    private AddressDto address;
}
