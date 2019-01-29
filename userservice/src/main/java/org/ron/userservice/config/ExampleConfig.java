package org.ron.userservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.userservice.config.property.ExampleProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ExampleProperties.class)
public class ExampleConfig {
    private final ExampleProperties exampleProperties;

    @PostConstruct
    public void init() {
        log.info("example.property from remote config repository is: {}", exampleProperties.getProperty());
    }
}
