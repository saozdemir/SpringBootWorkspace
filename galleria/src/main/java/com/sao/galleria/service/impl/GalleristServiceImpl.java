package com.sao.galleria.service.impl;

import com.sao.galleria.dto.GalleristDto;
import com.sao.galleria.dto.iu.GalleristDtoIU;
import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.mapper.GalleristMapper;
import com.sao.galleria.model.Address;
import com.sao.galleria.model.Gallerist;
import com.sao.galleria.repository.AddressRepository;
import com.sao.galleria.repository.GalleristRepository;
import com.sao.galleria.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 11 Jul 2025
 * <p>
 * @description:
 */
@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GalleristMapper galleristMapper;

    private Gallerist createGalleristFromDto(GalleristDtoIU galleristDtoIU) {
        Optional<Address> addressOptional = addressRepository.findById(galleristDtoIU.getAddressId());
        if (addressOptional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, galleristDtoIU.getAddressId().toString()));
        }
        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());
        BeanUtils.copyProperties(galleristDtoIU, gallerist);
        gallerist.setAddress(addressOptional.get());
        return gallerist;
    }

    @Override
    public GalleristDto saveGallerist(GalleristDtoIU galleristDtoIU) {
        Gallerist savedGallerist = galleristRepository.save(createGalleristFromDto(galleristDtoIU));
        return galleristMapper.toDto(savedGallerist);
    }
}
