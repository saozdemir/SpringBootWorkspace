package com.sao.galleria.service;

import com.sao.galleria.dto.SoldCarDto;
import com.sao.galleria.dto.iu.SoldCarDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
public interface ISoldCarService {
    SoldCarDto buyCar(SoldCarDtoIU soldCarDtoIu);
}
