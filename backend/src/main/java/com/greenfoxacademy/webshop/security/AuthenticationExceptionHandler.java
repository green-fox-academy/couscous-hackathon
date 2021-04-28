package com.greenfoxacademy.webshop.security;

import com.greenfoxacademy.webshop.exception.CustomExceptionHandler;
import com.greenfoxacademy.webshop.model.ErrorDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorDTO> disabledExceptionHandler(DisabledException ex) {
        logger.warn("DisabledException: " + ex.getMessage());
        return ResponseEntity.status(401)
                .body(new ErrorDTO(HttpStatus.valueOf(401), ex.getMessage()));
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        response.setStatus(401);
    }
}