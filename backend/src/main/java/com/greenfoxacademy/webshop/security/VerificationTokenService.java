package com.greenfoxacademy.webshop.security;

import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.repository.VerificationTokenRepository;
import com.greenfoxacademy.webshop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.UUID;

public class VerificationTokenService {

    private EmailService emailService;
    private VerificationTokenRepository tokenRepository;

    @Autowired
    public VerificationTokenService(EmailService emailService, VerificationTokenRepository tokenRepository) {
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    public void sendRegistrationConfirmationEmail(User newUser, Locale locale, String contextPath) {
        String recipientAddress = newUser.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
    }

    private User getUserByVerificationToken(String verificationToken) {
        tokenRepository.findByToken(verificationToken).getUser();
    }

    public void createVerificationToken(User user) {
        String tokenString = UUID.randomUUID().toString();
        VerificationToken myToken = new VerificationToken(tokenString, user);
        tokenRepository.save(myToken);
    }
}
