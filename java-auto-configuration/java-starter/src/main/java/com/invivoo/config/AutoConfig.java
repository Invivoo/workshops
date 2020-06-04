package com.invivoo.config;

import com.invivoo.IMeteoService;
import com.invivoo.MeteoService;
import com.invivoo.properties.MeteoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@ConditionalOnClass(MeteoService.class)
@EnableConfigurationProperties(MeteoProperties.class)
public class AutoConfig {

    @Autowired
    private MeteoProperties meteoProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "meteo", value = "temperature")
    public IMeteoService meteoService() {
        return new MeteoService(meteoProperties.getTemperature());
    }
}
