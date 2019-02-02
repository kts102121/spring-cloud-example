package org.ron.userservice.service;

import org.ron.userservice.model.User;

public interface UserService {
    User getUser(Integer userId);

    User getInventoryByUser(Integer userId);
}
