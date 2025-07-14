package com.sao.galleria.controller;

import com.sao.galleria.dto.CarDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.CarDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Jul 2025
 * <p>
 * @description:
 */
public interface ICarController {

    RootEntity<CarDto> saveCar(CarDtoIU carDtoIu);
}
