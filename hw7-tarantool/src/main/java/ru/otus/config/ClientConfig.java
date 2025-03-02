package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class ClientConfig {

    @Bean
    public RestTemplate userRestTemplate(@Value("${userService.url}") String url){
        RestTemplate restTemplate = new RestTemplateBuilder()
                .interceptors(List.of(new RequestIdInterceptor()))
                .rootUri(url)
                .build();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(UTF_8));
        return restTemplate;
    }
}
