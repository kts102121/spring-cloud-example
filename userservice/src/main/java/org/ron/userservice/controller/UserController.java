package org.ron.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.ron.userservice.config.property.ExampleProperties;
import org.ron.userservice.model.User;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/user")
public class UserController {
    private final ExampleProperties properties;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("id") Long id) {
        return new User(id, System.currentTimeMillis(), properties.getProperty() + ", kts1021!");
    }
}
