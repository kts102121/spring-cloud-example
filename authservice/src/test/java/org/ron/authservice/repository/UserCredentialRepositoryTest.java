package org.ron.authservice.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ron.authservice.model.UserCredential;
import org.ron.authservice.model.UserRole;
import org.ron.authservice.org.ron.config.BCryptConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(BCryptConfig.class)
public class UserCredentialRepositoryTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Before
    public void init() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("test1");
        userCredential.setPassword(passwordEncoder.encode("testtest"));

        UserRole userRole = new UserRole();
        userRole.setUsername(userCredential.getUsername());
        userRole.setRole("ROLE_USER");

        userCredential.addRole(userRole);

        userCredentialRepository.save(userCredential);
    }

    @Test
    public void crud() {
        Optional<UserCredential> selectedUserCredential = userCredentialRepository.findByUsername("test1");// <- without this, hibernate will not INSERT the data since they are not flushed yet

        assertThat(passwordEncoder.matches("testtest", selectedUserCredential.get().getPassword())).isTrue();
        assertThat(selectedUserCredential.get().getUsername()).isEqualTo("test1");
        assertThat(selectedUserCredential.get().getRoles()).isNotEmpty();
    }
}