package com.sao.galleria.controller.impl;

import com.sao.galleria.controller.BaseController;
import com.sao.galleria.controller.ICarController;
import com.sao.galleria.dto.CarDto;
import com.sao.galleria.dto.RootEntity;
import com.sao.galleria.dto.iu.CarDtoIU;
import com.sao.galleria.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Jul 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/galleria/car")
public class CarControllerImpl extends BaseController implements ICarController {

    @Autowired
    private ICarService carService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<CarDto> saveCar(@Valid @RequestBody CarDtoIU carDtoIu) {
        return ok(carService.saveCar(carDtoIu));
    }
}
