package org.ron.authservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class UserDTO implements Serializable {
    private String username;
    private UserContact contacts;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.contacts = user.getContacts();
    }
}
