package com.sao.galleria.controller;

import com.sao.galleria.dto.GalleristCarDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.GalleristCarDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
public interface IGalleristCarController {
    RootEntity<GalleristCarDto> saveGalleristCar(GalleristCarDtoIU galleristCarDtoIu);
}
