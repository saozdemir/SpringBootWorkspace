package net.saozdemir.ysdacademy.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 08 Mar 2026
 * <p>
 * @description: Tüm başlık sınıflarının (Category, Section, Unit, Lesson) ortak özelliklerini barındıran soyut sınıf.
 */
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public abstract class BaseEntity implements Serializable {
}
