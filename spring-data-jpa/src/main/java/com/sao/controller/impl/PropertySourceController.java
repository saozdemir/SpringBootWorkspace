package com.sao.controller.impl;

import com.sao.configuration.DataSource;
import com.sao.configuration.GlobalProperties;
import com.sao.configuration.GlobalPropertiesConfig;
import com.sao.configuration.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private GlobalPropertiesConfig globalPropertiesConfig;

    @GetMapping(path = "/datasource")
    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(globalProperties.getUrl());
        dataSource.setUserName(globalProperties.getUserName());
        dataSource.setPassword(globalProperties.getPassword());
        return dataSource;
    }

    @GetMapping(value = "/servers")
    public List<Server> getServers() {
        return globalPropertiesConfig.getServers();
    }

    @GetMapping(value = "/key")
    public String getKey() {
        return globalPropertiesConfig.getKey();
    }
}
