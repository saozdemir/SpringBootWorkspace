package net.saozdemir.ysdacademy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 08 Mar 2026
 * <p>
 * @description: Ana başlık (Category) sınıfının DTO'su.
 * CategoryDto, BaseDto'dan türetilmiştir ve Category'nin veri transferi için kullanılan bir temsilidir.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends BaseDto{
}
