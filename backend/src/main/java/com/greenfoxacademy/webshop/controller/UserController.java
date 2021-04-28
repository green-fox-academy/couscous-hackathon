package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.InvalidParametersException;
import com.greenfoxacademy.webshop.exception.MissingParametersException;
import com.greenfoxacademy.webshop.exception.ParamAlreadyExistException;
import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.model.UserResponseDTO;
import com.greenfoxacademy.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody User loginRequest) throws MissingParametersException {

        return ResponseEntity.ok(userService.validateLogin(loginRequest));
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody User registrationRequest)
            throws MissingParametersException, ParamAlreadyExistException, InvalidParametersException {

        return ResponseEntity.ok(userService.register(registrationRequest));
    }
}
