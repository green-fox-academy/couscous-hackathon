package com.greenfoxacademy.webshop.exception;

import com.greenfoxacademy.webshop.model.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {UserException.class,IllegalArgumentException.class})
    public ResponseEntity<ErrorDTO> BadRequestExceptionHandler(
            Exception ex) {
        return ResponseEntity.status(400)
                .body(new ErrorDTO(HttpStatus.valueOf(400), ex.getMessage()));
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> usernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
        return ResponseEntity.status(401)
                .body(new ErrorDTO(HttpStatus.valueOf(401), ex.getMessage()));
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(403)
                .body(new ErrorDTO(HttpStatus.valueOf(403), ex.getMessage()));
    }
    }
