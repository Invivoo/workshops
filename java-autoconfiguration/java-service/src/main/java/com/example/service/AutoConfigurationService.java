package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class AutoConfigurationService {

    private String name;

    public AutoConfigurationService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
