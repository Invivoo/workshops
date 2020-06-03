package com.example.starter.config;

import com.example.service.AutoConfigurationService;
import com.example.starter.properties.StarterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StarterProperties.class)
@ConditionalOnClass(AutoConfigurationService.class)
public class StarterAutoConfiguration {
    @Autowired
    private StarterProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "starter", value = "message")
    public AutoConfigurationService autoConfigurationService() {
        return new AutoConfigurationService(properties.getMessage());
    }
}
