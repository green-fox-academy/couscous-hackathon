package com.greenfoxacademy.webshop.exception;

import com.greenfoxacademy.webshop.model.ErrorDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = {UserException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorDTO> badRequestExceptionHandler(Exception ex) {
        if (ex instanceof UserException) {
            logger.warn("UserException: " + ex.getMessage());
        } else if (ex instanceof IllegalArgumentException) {
            logger.warn("IllegalArgumentException: " + ex.getMessage());
        }
        return ResponseEntity.status(400)
                .body(new ErrorDTO(HttpStatus.valueOf(400), ex.getMessage()));
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> usernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
        logger.warn("UsernameNotFoundException: " + ex.getMessage());
        return ResponseEntity.status(401)
                .body(new ErrorDTO(HttpStatus.valueOf(401), ex.getMessage()));
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex) {
        logger.warn("BadCredentialsException: " + ex.getMessage());
        return ResponseEntity.status(403)
                .body(new ErrorDTO(HttpStatus.valueOf(403), ex.getMessage()));
    }
}
