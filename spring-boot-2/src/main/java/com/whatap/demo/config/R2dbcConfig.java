package com.whatap.demo.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.whatap.demo.r2dbc")
@EnableConfigurationProperties(R2dbcProperties.class)
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    private final R2dbcProperties properties;

    public R2dbcConfig(R2dbcProperties properties) {
        this.properties = properties;
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(properties.getUrl());
    }
}