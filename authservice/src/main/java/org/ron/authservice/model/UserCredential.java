package org.ron.authservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ron.authservice.exception.UserDetailsException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(indexes = {@Index(name = "IDX_USERNAME", columnList = "username")})
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @JsonInclude
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
