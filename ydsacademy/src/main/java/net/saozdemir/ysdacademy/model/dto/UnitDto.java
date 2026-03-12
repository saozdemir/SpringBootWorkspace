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
 * @description: Ünite (Unit) sınıfının DTO'su. Unit sınıfı 3.Seviyeyi temsil eder.
 * İçerisinde dersleri (Lesson) barındırır.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UnitDto extends BaseDto {
}
