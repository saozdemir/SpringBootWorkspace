package com.sao.galleria.service.impl;

import com.sao.galleria.dto.CarDto;
import com.sao.galleria.dto.iu.CarDtoIU;
import com.sao.galleria.mapper.CarMapper;
import com.sao.galleria.model.Car;
import com.sao.galleria.repository.CarRepository;
import com.sao.galleria.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Jul 2025
 * <p>
 * @description:
 */
@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    private Car createCarFromDto(CarDtoIU carDtoIu) {
        Car car = new Car();
        car.setCreateTime(new Date());
        BeanUtils.copyProperties(carDtoIu, car);
        return car;
    }

    @Override
    public CarDto saveCar(CarDtoIU carDtoIu) {
        Car savedCar = carRepository.save(createCarFromDto(carDtoIu));
        return carMapper.toDto(savedCar);
    }
}
