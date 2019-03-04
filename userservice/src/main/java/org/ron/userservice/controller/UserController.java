package org.ron.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.ron.userservice.client.InventoryRestTemplateClient;
import org.ron.userservice.model.User;
import org.ron.userservice.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("id") Integer userId) {
        return userService.getUser(userId);
    }

    @GetMapping(value = {"{id}/inventory"})
    public User getInventryByUser(@PathVariable("id") Integer userId) {
        return userService.getInventoryByUser(userId);
    }

    @PostMapping
    public void saveUser(final @RequestBody User user) {
        userService.saveUser(user);
    }
}
