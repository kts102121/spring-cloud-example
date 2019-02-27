package org.ron.authservice.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.authservice.model.User;
import org.ron.authservice.service.UserService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
