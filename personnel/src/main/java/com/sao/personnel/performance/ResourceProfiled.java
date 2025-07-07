package com.sao.personnel.performance;
import java.lang.annotation.*;
/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 05 Tem 2025
 * <p>
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceProfiled {
    String value() default ""; // opsiyonel açıklama/metrik adı
}
