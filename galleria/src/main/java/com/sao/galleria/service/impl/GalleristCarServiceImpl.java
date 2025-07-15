package com.sao.galleria.service.impl;

import com.sao.galleria.dto.GalleristCarDto;
import com.sao.galleria.dto.iu.GalleristCarDtoIU;
import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.mapper.CarMapper;
import com.sao.galleria.mapper.GalleristCarMapper;
import com.sao.galleria.mapper.GalleristMapper;
import com.sao.galleria.model.Car;
import com.sao.galleria.model.Gallerist;
import com.sao.galleria.model.GalleristCar;
import com.sao.galleria.repository.CarRepository;
import com.sao.galleria.repository.GalleristCarRepository;
import com.sao.galleria.repository.GalleristRepository;
import com.sao.galleria.service.IGalleristCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 14 Tem 2025
 * <p>
 * @description:
 */
@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristCarMapper galleristCarMapper;

    @Autowired
    private GalleristMapper galleristMapper;

    @Autowired
    private CarMapper carMapper;

    private GalleristCar createGalleristCarFromDto(GalleristCarDtoIU galleristCarDtoIu) {
        Optional<Gallerist> galleristOptional = galleristRepository.findById(galleristCarDtoIu.getGalleristId());

        if (galleristOptional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, galleristCarDtoIu.getGalleristId().toString()));
        }

        Optional<Car> carOptional = carRepository.findById(galleristCarDtoIu.getCarId());

        if (carOptional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, galleristCarDtoIu.getCarId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(galleristOptional.get());
        galleristCar.setCar(carOptional.get());
        return galleristCar;
    }

    @Override
    public GalleristCarDto saveGalleristCar(GalleristCarDtoIU galleristCarDtoIu) {
        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCarFromDto(galleristCarDtoIu));
        return galleristCarMapper.toDto(savedGalleristCar);
    }
}
