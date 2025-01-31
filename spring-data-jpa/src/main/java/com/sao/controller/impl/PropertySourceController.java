package com.sao.controller.impl;

import com.sao.configuration.DataSource;
import com.sao.configuration.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 31 Oca 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/rest/api/property")
public class PropertySourceController {

    /** Container da oluşan bean çağrıldı.*/
    @Autowired
    private GlobalProperties globalProperties;

    @GetMapping(path = "/datasource")
    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(globalProperties.getUrl());
        dataSource.setUserName(globalProperties.getUserName());
        dataSource.setPassword(globalProperties.getPassword());
        return dataSource;
    }
}
