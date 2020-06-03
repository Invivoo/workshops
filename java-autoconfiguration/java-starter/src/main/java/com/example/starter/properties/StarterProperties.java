package com.example.starter.properties;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "starter")
@Slf4j
@Data
public class StarterProperties {

    private String message;
}

