package com.sao.galleria.service;

import com.sao.galleria.dto.CarDto;
import com.sao.galleria.dto.iu.CarDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Jul 2025
 * <p>
 * @description:
 */
public interface ICarService {
    CarDto saveCar(CarDtoIU carDtoIu);
}
