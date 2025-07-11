package com.sao.galleria.controller;

import com.sao.galleria.dto.GalleristDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.GalleristDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public interface IGalleristController {

    RootEntity<GalleristDto> saveGallerist(GalleristDtoIU galleristDtoIu);
}
