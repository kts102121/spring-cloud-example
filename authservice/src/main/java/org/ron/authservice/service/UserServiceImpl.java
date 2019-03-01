package org.ron.authservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.authservice.event.source.MessageService;
import org.ron.authservice.exception.UserDetailsException;
import org.ron.authservice.model.User;
import org.ron.authservice.model.UserCredential;
import org.ron.authservice.model.UserDTO;
import org.ron.authservice.repository.UserCredentialRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final MessageService messageService;
    private final UserCredentialRepository userCredentialRepository;

    @Override
    @Transactional
    public void saveUser(User user) throws UserDetailsException {
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
        messageService.sendMessage("POST", new UserDTO(user));
    }
}
