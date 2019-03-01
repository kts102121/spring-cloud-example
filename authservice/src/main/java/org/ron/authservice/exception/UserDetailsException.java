package org.ron.authservice.exception;

public class UserDetailsException extends RuntimeException {
    public UserDetailsException(String message) {
        super(message);
    }
}
