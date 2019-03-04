package org.ron.userservice.model;

import lombok.Getter;
import lombok.Setter;
import org.ron.userservice.event.UserCreatedEvent;
import org.ron.userservice.exception.model.UserDetailsException;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "Account")
public class User extends AbstractAggregateRoot<User> implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Embedded
    private UserContact contacts;

    @Transient
    private UserCredential credentials;

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

    public User publish() {
        this.registerEvent(new UserCreatedEvent(this));

        return this;
    }
}
