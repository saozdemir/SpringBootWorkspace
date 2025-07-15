package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.ISoldCarController;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.SoldCarDto;
import com.sao.galleria.dto.iu.SoldCarDtoIU;
import com.sao.galleria.service.ISoldCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/galleria/sold-cars")
public class SoldCarControllerImpl extends BaseController implements ISoldCarController {

    @Autowired
    private ISoldCarService soldCarService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<SoldCarDto> buyCar(@Valid @RequestBody SoldCarDtoIU soldCarDtoIu) throws Exception {
        try {
            return ok(soldCarService.buyCar(soldCarDtoIu));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
