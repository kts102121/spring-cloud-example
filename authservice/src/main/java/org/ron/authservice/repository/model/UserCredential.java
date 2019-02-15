package org.ron.authservice.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class UserCredential {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = UserRole.class, mappedBy = "userCredential", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserRole> roles;
}
