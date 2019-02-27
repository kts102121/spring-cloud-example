package org.ron.authservice.repository;

import org.ron.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByUsernameOrContacts_Email(String username, String email);
}
