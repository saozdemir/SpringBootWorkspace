package com.sao.galleria.dto;

import com.sao.galleria.model.Car;
import com.sao.galleria.model.Gallerist;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCarDto extends BaseDto{

    private GalleristDto gallerist;

    private CarDto car;
}
