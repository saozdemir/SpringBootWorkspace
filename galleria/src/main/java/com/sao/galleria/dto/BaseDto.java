package com.sao.galleria.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
@Data
public abstract class BaseDto implements Serializable {
    private Long id;

    private Date createTime;
}
