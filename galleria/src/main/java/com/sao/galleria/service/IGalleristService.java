package com.sao.galleria.service;

import com.sao.galleria.dto.GalleristDto;
import com.sao.galleria.dto.iu.GalleristDtoIU;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
public interface IGalleristService {

    GalleristDto saveGallerist(GalleristDtoIU galleristDtoIU);
}
