package org.ron.authservice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.authservice.exception.model.UserDetailsException;
import org.ron.authservice.model.User;
import org.ron.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Gson gson;
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) throws UserDetailsException {
        User existingUser = userRepository.findByUsernameOrContacts_Email(user.getUsername(), user.getContacts().getEmail());

        log.info("user: {}", gson.toJson(user, new TypeToken<User>(){}.getType()));
        log.info("existingUser: {}", gson.toJson(existingUser, new TypeToken<User>(){}.getType()));
        if (existingUser != null) {
            user.exists(existingUser);
        }

        userRepository.save(user);
    }
}
