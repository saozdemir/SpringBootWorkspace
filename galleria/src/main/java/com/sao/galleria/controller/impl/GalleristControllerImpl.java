package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.IGalleristController;
import com.sao.galleria.dto.GalleristDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.GalleristDtoIU;
import com.sao.galleria.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/galleria/gallerist")
public class GalleristControllerImpl extends BaseController implements IGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<GalleristDto> saveGallerist(@Valid @RequestBody GalleristDtoIU galleristDtoIu) {
        try {
            return ok(galleristService.saveGallerist(galleristDtoIu));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
