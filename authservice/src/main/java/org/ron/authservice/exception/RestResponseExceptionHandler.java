package org.ron.authservice.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.ron.authservice.exception.model.ErrorResponse;
import org.ron.authservice.exception.model.UserDetailsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = UserDetailsException.class)
    protected ResponseEntity<Object> handleConflict(UserDetailsException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
