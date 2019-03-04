package org.ron.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>();
}
