package com.sao.controller.impl;

import com.sao.controller.IHomeController;
import com.sao.dto.HomeDto;
import com.sao.services.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Oca 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/rest/api/home")
public class HomeControllerImpl implements IHomeController {

    @Autowired
    private IHomeService homeService;

    @GetMapping(path = "/{id}")
    @Override
    public HomeDto findHomeById(@PathVariable(name = "id") Long id) {
        return homeService.findHomeById(id);
    }
}
