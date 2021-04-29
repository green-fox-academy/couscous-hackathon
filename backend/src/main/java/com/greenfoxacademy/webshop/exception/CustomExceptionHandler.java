package com.greenfoxacademy.webshop.exception;

import com.greenfoxacademy.webshop.model.ErrorDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.mail.MessagingException;


@ControllerAdvice
public class CustomExceptionHandler {

  private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class);

  @ExceptionHandler(value = {UserException.class, IllegalArgumentException.class,
      ItemNotFoundException.class, ParamAlreadyExistException.class, CartNotFoundException.class})
  public ResponseEntity<ErrorDTO> badRequestExceptionHandler(Exception ex) {
    if (ex instanceof UserException) {
      logger.warn("UserException: " + ex.getMessage());
    } else if (ex instanceof IllegalArgumentException) {
      logger.warn("IllegalArgumentException: " + ex.getMessage());
    }

    return ResponseEntity.status(400)
        .body(new ErrorDTO(HttpStatus.valueOf(400), ex.getMessage()));
  }

  @ExceptionHandler(value = {BadCredentialsException.class, DisabledException.class, UsernameNotFoundException.class})
  public ResponseEntity<ErrorDTO> handleAuthenticationException(AuthenticationException ex) {
    if (ex instanceof BadCredentialsException) {
      logger.warn("BadCredentialsException: " + ex.getMessage());
    } else if (ex instanceof DisabledException) {
      logger.warn("DisabledException: " + ex.getMessage());
    } else if (ex instanceof UsernameNotFoundException) {
      logger.warn("UsernameNotFoundException: " + ex.getMessage());
    }

    return ResponseEntity.status(401)
        .body(new ErrorDTO(HttpStatus.valueOf(401), ex.getMessage()));
  }

  @ExceptionHandler(value = MessagingException.class)
  public ResponseEntity<ErrorDTO> messagingExceptionHandler(MessagingException ex) {
    logger.warn("MessagingException: " + ex.getMessage());
    return ResponseEntity.status(500)
        .body(new ErrorDTO(HttpStatus.valueOf(500), ex.getMessage()));
  }
}
