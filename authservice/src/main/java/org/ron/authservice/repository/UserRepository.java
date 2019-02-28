package org.ron.authservice.repository;

import org.ron.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    Optional<User> findByUsernameOrContacts_Email(String username, String email);
}
