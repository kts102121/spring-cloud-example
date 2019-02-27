package org.ron.authservice.model;

import com.google.gson.Gson;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.ron.authservice.exception.model.UserDetailsException;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@Entity(name = "Account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {

    @Id
    @NotNull
    @NotEmpty
    private String username;

    @Embedded
    private UserContact contacts;

    public String exists(User existingUser) {
        System.out.println("user: " + this.toString() + ", existingUser: " + existingUser.toString());

        if (this.contacts.getEmail().equals(existingUser.getContacts().getEmail()) && !this.username.equals(existingUser.getUsername())) {
            throw new UserDetailsException("email already exists");
        } else if (!this.contacts.getEmail().equals(existingUser.getContacts().getEmail()) && this.username.equals(existingUser.getUsername())) {
            throw new UserDetailsException("username already exists");
        } else {
            throw new UserDetailsException("invalid username and email address");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email=" + contacts.getEmail() +
                '}';
    }
}

