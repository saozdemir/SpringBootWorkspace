package com.sao.galleria.controller;

import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.SoldCarDto;
import com.sao.galleria.dto.iu.SoldCarDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
public interface ISoldCarController {

    RootEntity<SoldCarDto> buyCar(SoldCarDtoIU soldCarDtoIu) throws Exception;
}
