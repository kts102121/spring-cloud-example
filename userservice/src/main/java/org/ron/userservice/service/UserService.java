package org.ron.userservice.service;

import org.ron.userservice.model.User;

public interface UserService {
    User getUser(String username);

    User getInventoryByUser(Integer userId);

    void saveUser(User user);
}
