package org.ron.userservice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.userservice.client.InventoryRestTemplateClient;
import org.ron.userservice.model.Inventory;
import org.ron.userservice.model.User;
import org.ron.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Gson gson;
    private final UserRepository userRepository;
    private final InventoryRestTemplateClient inventoryClient;

    @Override
    public User getUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NullPointerException("userId-"+userId);
        }

        return user.get();
    }

    @Override
    public User getInventoryByUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NullPointerException("userId-" + userId);
        }

        List<Inventory> inventories = inventoryClient.getInventory(userId);
        log.info("inventories: {}", gson.toJson(inventories, new TypeToken<List<Inventory>>() {}.getType()));
        user.get().setInventories(inventories);

        return user.get();
    }

    private List<Inventory> getInventory(Integer userId) {
        return inventoryClient.getInventory(userId);
    }
}
