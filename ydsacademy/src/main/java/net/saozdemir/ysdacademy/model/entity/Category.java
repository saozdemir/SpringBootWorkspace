package net.saozdemir.ysdacademy.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 08 Mar 2026
 * <p>
 * @description: Ana başlık (Category) sınıfı 1.Seviyeyi temsil eder. İçerisinde alt başlıklar (Section) barındırır.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Category extends BaseEntity{
}
