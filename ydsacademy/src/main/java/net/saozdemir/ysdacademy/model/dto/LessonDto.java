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
 * @description: Ders (Lesson) sınıfının veri transfer nesnesi.
 * Ders başlığı, açıklaması, video URL'si gibi bilgileri içerir. 4.Seviyeyi temsil eder.
 * Video içerir. En alt başlık sınıfıdır.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LessonDto extends BaseDto {
}
