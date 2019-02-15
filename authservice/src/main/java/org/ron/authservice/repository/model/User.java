package org.ron.authservice.repository.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String username;

    private String userEmail;

    private UserCredential userCredential;

    private List<UserRole> roles;
}
