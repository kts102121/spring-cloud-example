package org.ron.authservice.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "user_username", nullable = false)
    private String username;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonBackReference
    private UserCredential userCredential;

    public UserRole(String username, String role) {
        this.username = username;
        this.role = role;
    }

}
