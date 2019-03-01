package org.ron.userservice.model;

import lombok.Getter;
import lombok.Setter;
import org.ron.userservice.exception.model.UserDetailsException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "Account")
public class User implements Serializable {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Embedded
    private UserContact contacts;

    @Transient
    private List<Inventory> inventories;

    public String exists(User existingUser) {
        if (this.contacts.getEmail().equals(existingUser.getContacts().getEmail()) && !this.username.equals(existingUser.getUsername())) {
            throw new UserDetailsException("email already exists");
        } else if (!this.contacts.getEmail().equals(existingUser.getContacts().getEmail()) && this.username.equals(existingUser.getUsername())) {
            throw new UserDetailsException("username already exists");
        } else {
            throw new UserDetailsException("invalid username and email address");
        }
    }
}
