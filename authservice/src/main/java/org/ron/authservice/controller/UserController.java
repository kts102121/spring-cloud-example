package org.ron.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.ron.authservice.repository.model.User;
import org.ron.authservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController(value = "/v1/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public void createUser(@RequestBody User user) {
        user.getUserCredential().setUsername(user.getUsername());
        user.getUserCredential().setPassword(passwordEncoder.encode(user.getUserCredential().getPassword()));

        userService.createUserCredentials(user.getUserCredential());
    }
}
