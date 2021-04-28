package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.InvalidParametersException;
import com.greenfoxacademy.webshop.exception.MissingParametersException;
import com.greenfoxacademy.webshop.exception.ParamAlreadyExistException;
import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.model.UserResponseDTO;
import com.greenfoxacademy.webshop.repository.UserRepository;
import com.greenfoxacademy.webshop.repository.VerificationTokenRepository;
import com.greenfoxacademy.webshop.security.CustomUserDetailsService;
import com.greenfoxacademy.webshop.security.JwtUtil;
import com.greenfoxacademy.webshop.security.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class UserService {

    private UserRepository userRepository;
    private CustomUserDetailsService customUserDetailsService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
private VerificationTokenService tokenService;

    @Autowired
    public UserService(
            UserRepository userRepository, CustomUserDetailsService customUserDetailsService,
            JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public UserResponseDTO validateLogin(User loginRequest) throws MissingParametersException {
        List<String> missingParams = getMissingParams(loginRequest, false);
        if (missingParams.size() != 0) {
            throw new MissingParametersException("Missing parameters found, please amend your input: " + missingParams.toString());
        }
        User currentUser = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No user found with the username " + loginRequest.getUsername()));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentUser.getUsername(), loginRequest.getPassword()));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(currentUser.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return new UserResponseDTO("ok", null, token);
    }

    private List<String> getMissingParams(User user, boolean isRegistration) {
        List<String> missingParams = new ArrayList<>();
        if (user.getUsername() == null || user.getUsername().equals("")) {
            missingParams.add("username");
        }
        if ((user.getEmail() == null || user.getEmail().equals("")) && isRegistration) {
            missingParams.add("email");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            missingParams.add("password");
        }
        return missingParams;
    }

    public User getAuthenticatedUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found, please log in again"));
    }

    public UserResponseDTO register(User registrationRequest, HttpServletRequest httpRequest)
            throws MissingParametersException, ParamAlreadyExistException, InvalidParametersException {

        checkRegistrationRequestParams(registrationRequest);
        User newUser = userRepository.save(
                new User(registrationRequest.getUsername(), registrationRequest.getEmail(),
                        hashPassword(registrationRequest.getPassword())));

        tokenService.sendRegistrationConfirmationEmail(newUser, httpRequest.getLocale(), httpRequest.getContextPath());

        return new UserResponseDTO("ok", registrationRequest.getUsername()
                + " rockz! Welcome to the Meme creator. You can now log in.", null);
    }



    private void checkRegistrationRequestParams(User registrationRequest)
            throws MissingParametersException, ParamAlreadyExistException, InvalidParametersException {
        List<String> missingParams = getMissingParams(registrationRequest, true);
        if (missingParams.size() != 0) {
            throw new MissingParametersException(missingParams.toString());
        }
        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new ParamAlreadyExistException("Username already exists, please choose another one");
        }
        if (!isEmailValid(registrationRequest.getEmail())) {
            throw new InvalidParametersException("The format of the email address is not valid");
        }
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new ParamAlreadyExistException("User with this email address already exists");
        }
    }

    private String hashPassword(String plainTextPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(plainTextPassword);
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}