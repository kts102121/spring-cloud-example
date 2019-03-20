package org.ron.userservice.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerProperties;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.ron.userservice.client.InventoryRestTemplateClient;
import org.ron.userservice.model.Inventory;
import org.ron.userservice.model.User;
import org.ron.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CircuitBreaker circuitBreaker;
    private final InventoryRestTemplateClient inventoryClient;

    public UserServiceImpl(UserRepository userRepository, CircuitBreakerRegistry circuitBreakerRegistry, CircuitBreakerProperties circuitBreakerProperties, InventoryRestTemplateClient inventoryClient) {
        this.userRepository = userRepository;
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("remoteInventoryService", () -> circuitBreakerProperties.createCircuitBreakerConfig("remoteInventoryService"));
        this.inventoryClient = inventoryClient;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public User getInventoryByUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NullPointerException("userId-" + userId);
        }

        List<Inventory> inventories = getInventory(userId);
        user.get().setInventories(inventories);

        return user.get();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
/*        Optional<UserCredential> existingUserCredential = userCredentialRepository.findByUsername(user.getUsername());

        existingUserCredential.ifPresent(user.getCredentials()::exists);

        UserCredential credential = user.getCredentials();
        credential.setUsername(user.getUsername());
        credential.setPassword(passwordEncoder.encode(user.getCredentials().getPassword()));
        credential.getRoles().forEach(role -> {
            role.setUsername(user.getUsername());
            credential.addRole(role);
        });

        userCredentialRepository.save(credential);*/
        Optional<User> existingUser = userRepository.findByUsernameOrContacts_Email(user.getUsername(), user.getContacts().getEmail());
        existingUser.ifPresent(user::exists);
        userRepository.save(user.publish());
    }

    private List<Inventory> getInventory(Integer userId) {
        Supplier<List<Inventory>> remoteInventoryResponse = CircuitBreaker.decorateSupplier(circuitBreaker, () -> inventoryClient.getInventory(userId));

        return Try.ofSupplier(remoteInventoryResponse)
                .recover(throwable -> Collections.singletonList(fallbackInventory())).get();
    }

    private Inventory fallbackInventory() {
        return new Inventory(0, "Something is wrong. Please try again later");
    }
}
