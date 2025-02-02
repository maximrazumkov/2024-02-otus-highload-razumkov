package ru.otus.config;

import io.tarantool.driver.api.TarantoolClient;
import io.tarantool.driver.api.TarantoolClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TarantoolConfig {

    @Value("${tarantool.uri}")
    private String tarantoolUri;

    @Value("${tarantool.port}")
    private int tarantoolPort;

    @Bean
    public TarantoolClient tarantoolClient() {
        return TarantoolClientFactory.createClient()
                .withAddress(tarantoolUri, tarantoolPort)
                .build();
    }
}
