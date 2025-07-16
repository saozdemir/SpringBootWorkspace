package com.sao.usermanagement.dto;

import com.sao.usermanagement.enums.Deleted;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Data
public abstract class BaseDto {

    private Long id;

    private Deleted deleted;

    private Date createTime;

}
