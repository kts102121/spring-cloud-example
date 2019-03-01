package org.ron.authservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.ron.authservice.exception.UserDetailsException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class UserCredential {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "credential", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<UserRole> roles = new HashSet<>();

    public void exists(UserCredential existingUserCredential) {
        if (this.username.equals(existingUserCredential.getUsername())) {
            throw new UserDetailsException("Existing user");
        }
    }

    public void addRole(UserRole role) {
        this.roles.add(role);
        role.setCredential(this);
    }
}
