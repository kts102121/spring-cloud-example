package org.ron.authservice.service;

import org.ron.authservice.model.User;
import org.ron.authservice.model.UserCredential;
import org.ron.authservice.repository.UserCredentialRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserCredentialRepository userCredentialRepository;

    public UserCredentialServiceImpl(BCryptPasswordEncoder passwordEncoder, UserCredentialRepository userCredentialRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public void saveUserCredential(User user) {
        Optional<UserCredential> existingUserCredential = userCredentialRepository.findByUsername(user.getUsername());

        existingUserCredential.ifPresent(user.getCredentials()::exists);

        UserCredential credential = user.getCredentials();
        credential.setUsername(user.getUsername());
        credential.setPassword(passwordEncoder.encode(user.getCredentials().getPassword()));
        credential.getRoles().forEach(role -> {
            role.setUsername(user.getUsername());
            credential.addRole(role);
        });

        userCredentialRepository.save(credential);
    }
}
