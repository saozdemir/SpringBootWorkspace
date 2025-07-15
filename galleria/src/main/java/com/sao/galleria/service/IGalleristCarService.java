package com.sao.galleria.service;

import com.sao.galleria.dto.GalleristCarDto;
import com.sao.galleria.dto.iu.GalleristCarDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
public interface IGalleristCarService {

    GalleristCarDto saveGalleristCar(GalleristCarDtoIU galleristCarDtoIu);
}
