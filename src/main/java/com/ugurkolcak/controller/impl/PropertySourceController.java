package com.ugurkolcak.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugurkolcak.configuration.GlobalProperties;

@RestController
@RequestMapping("/rest/api/properties")
public class PropertySourceController {

    private final GlobalProperties globalProperties;

    public PropertySourceController(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }

    @GetMapping("/datasource")
    public Map<String, String> getDatasource() {
        Map<String, String> datasource = new HashMap<>();
        datasource.put("url", globalProperties.getUrl());
        datasource.put("username", globalProperties.getUsername());
        datasource.put("password", globalProperties.getPassword());
        return datasource;
    }

}
