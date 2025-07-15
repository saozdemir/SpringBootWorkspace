package com.sao.galleria.service.impl;

import com.sao.galleria.dto.CurrencyRatesResponse;
import com.sao.galleria.dto.SoldCarDto;
import com.sao.galleria.dto.iu.SoldCarDtoIU;
import com.sao.galleria.enums.CarStatusType;
import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.mapper.SoldCarMapper;
import com.sao.galleria.model.Car;
import com.sao.galleria.model.Customer;
import com.sao.galleria.model.SoldCar;
import com.sao.galleria.repository.CarRepository;
import com.sao.galleria.repository.CustomerRepository;
import com.sao.galleria.repository.GalleristRepository;
import com.sao.galleria.repository.SoldCarRepository;
import com.sao.galleria.service.ICurrencyRatesService;
import com.sao.galleria.service.ISoldCarService;
import com.sao.galleria.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
@Service
public class SoldCarServiceImpl implements ISoldCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Autowired
    private SoldCarRepository soldCarRepository;

    @Autowired
    private SoldCarMapper soldCarMapper;

    private BigDecimal convertCustomerAmountToUsd(Customer customer) {
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getYesterdayDate(), DateUtils.getYesterdayDate());
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getRate());
        return customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
    }

    private boolean checkAmount(SoldCarDtoIU soldCarDtoIu) {
        Optional<Customer> optionalCustomer = customerRepository.findById(soldCarDtoIu.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, soldCarDtoIu.getCustomerId().toString()));
        }

        Optional<Car> optionalCar = carRepository.findById(soldCarDtoIu.getCarId());
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, soldCarDtoIu.getCarId().toString()));
        }

        BigDecimal customerUsdAmount = convertCustomerAmountToUsd(optionalCustomer.get());

        if (customerUsdAmount.compareTo(optionalCar.get().getPrice()) == 0 || customerUsdAmount.compareTo(optionalCar.get().getPrice()) > 0) {
            return true;
        } else {
            return false;
        }

    }

    private BigDecimal remainingCustomerAmount(Customer customer, Car car) {
        BigDecimal customerUsdAmount = convertCustomerAmountToUsd(customer);
        BigDecimal remainingCustomerUsdAmount = customerUsdAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getYesterdayDate(), DateUtils.getYesterdayDate());
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getRate());
        return remainingCustomerUsdAmount.multiply(usd);
    }

    private boolean checkCarStatus(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent() && optionalCar.get().getCarStatusType().name().equals(CarStatusType.SOLD.name())) {
            return false;
        }
        return true;
    }

    private SoldCar createSoldCar(SoldCarDtoIU soldCarDtoIu) {
        SoldCar soldCar = new SoldCar();
        soldCar.setCreateTime(new Date());
        soldCar.setCustomer(customerRepository.findById(soldCarDtoIu.getCustomerId()).orElse(null));
        soldCar.setGallerist(galleristRepository.findById(soldCarDtoIu.getGalleristId()).orElse(null));
        soldCar.setCar(carRepository.findById(soldCarDtoIu.getCarId()).orElse(null));
        return soldCar;
    }

    @Override
    public SoldCarDto buyCar(SoldCarDtoIU soldCarDtoIu) {
        if (!checkCarStatus(soldCarDtoIu.getCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_ALREADY_SOLD, soldCarDtoIu.getCarId().toString()));
        }

        if (!checkAmount(soldCarDtoIu)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_NOT_ENOUGH, soldCarDtoIu.getCustomerId().toString()));
        }

        SoldCar savedSoldCar = soldCarRepository.save(createSoldCar(soldCarDtoIu));

        Car car = savedSoldCar.getCar();
        car.setCarStatusType(CarStatusType.SOLD);
        carRepository.save(car);

        Customer customer = savedSoldCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
        customerRepository.save(customer);

        return soldCarMapper.toDto(savedSoldCar);
    }
}
