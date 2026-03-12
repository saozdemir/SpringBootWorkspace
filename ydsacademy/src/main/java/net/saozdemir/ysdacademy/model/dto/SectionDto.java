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
 * @description: Bölüm (Section) sınıfının DTO'su. Bölüm, 2.Seviyeyi temsil eder ve içerisinde alt başlıklar (Unit) barındırır.
 * Bu DTO, Bölüm ile ilgili veri transferi işlemlerinde kullanılır.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SectionDto extends BaseDto {
}
