package com.sao.controller.impl;

import com.sao.controller.IRestPersonnelController;
import com.sao.model.entity.Personnel;
import com.sao.service.IPersonnelService;
import com.sao.utils.RestPageableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class RestPersonnelControllerImpl implements IRestPersonnelController {

    @Autowired
    private IPersonnelService personnelService;

    /** /8080?pageNumber=0&pageSize=5&columnName=createTime&asc=true */
    //@ModelAttribute:
    @GetMapping(path = "/list/pageable")
    @Override
    public Page<Personnel> findAllPageable(@ModelAttribute RestPageableRequest pageableRequest) {//@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "pageSize") int pageSize
        /** Pageable türünde bir nesne üretmek için kullanıldı. (PageRequest Pageable'ın childi dır.)*/
        PageRequest pageable = PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        return personnelService.findAllPageable(pageable);
    }



}
