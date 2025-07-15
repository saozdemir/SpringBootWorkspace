package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.IGalleristCarController;
import com.sao.galleria.dto.GalleristCarDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.GalleristCarDtoIU;
import com.sao.galleria.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "api/galleria/gallerist-car")
public class GalleristCarController extends BaseController implements IGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<GalleristCarDto> saveGalleristCar(@Valid @RequestBody GalleristCarDtoIU galleristCarDtoIu) {
        try {
            return ok(galleristCarService.saveGalleristCar(galleristCarDtoIu));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
