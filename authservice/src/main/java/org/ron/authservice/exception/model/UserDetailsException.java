package org.ron.authservice.exception.model;

public class UserDetailsException extends RuntimeException {
    public UserDetailsException(String message) {
        super(message);
    }
}
