package com.sao.controller.impl;

import com.sao.controller.IRestBaseController;
import com.sao.controller.IRestPersonnelController;
import com.sao.model.dto.PersonnelDto;
import com.sao.model.entity.Personnel;
import com.sao.service.IPersonnelService;
import com.sao.utils.RestPageableEntity;
import com.sao.utils.RestPageableRequest;
import com.sao.utils.RestRootEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 18 Şub 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/rest/api/personnel")
public class RestPersonnelControllerImpl extends RestBaseControllerImpl implements IRestPersonnelController {

    @Autowired
    private IPersonnelService personnelService;

    /** /8080?pageNumber=0&pageSize=5&columnName=createTime&asc=true */
    //@ModelAttribute:
    @GetMapping(path = "/list/pageable")
    @Override
    public RestRootEntity<RestPageableEntity<PersonnelDto>>  findAllPageable(@ModelAttribute RestPageableRequest pageableRequest) {//@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "pageSize") int pageSize
        /** Pageable türünde bir nesne üretmek için kullanıldı. (PageRequest Pageable'ın childi dır.)*/
        Pageable pageable = super.toPageable(pageableRequest);
        Page<Personnel> page = personnelService.findAllPageable(pageable);
        RestPageableEntity<PersonnelDto> pageableResponse =  super.toPageableResponse(page, personnelService.toDTOList(page.getContent()));/** DTO ya dönüştürülmüş liste*/
        return super.ok(pageableResponse);
    }
}
