package org.ron.authservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.authservice.model.User;
import org.ron.authservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(final @Valid @RequestBody User user) {
        userService.saveUser(user);
    }
}
