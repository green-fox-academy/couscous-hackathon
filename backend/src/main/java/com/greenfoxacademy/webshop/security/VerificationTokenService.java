package com.greenfoxacademy.webshop.security;

import com.greenfoxacademy.webshop.exception.VerificationTokenException;
import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.repository.VerificationTokenRepository;
import com.greenfoxacademy.webshop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Component
public class VerificationTokenService {

    private EmailService emailService;
    private VerificationTokenRepository tokenRepository;

    @Autowired
    public VerificationTokenService(EmailService emailService, VerificationTokenRepository tokenRepository) {
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    public void sendRegistrationConfirmationEmail(User newUser, String url) throws MessagingException {
        String recipientAddress = newUser.getEmail();
        String token = createVerificationToken(newUser);
        emailService.sendRegistrationMessage(recipientAddress, url, token);
    }

    private String createVerificationToken(User user) {
        String tokenString = UUID.randomUUID().toString();
        VerificationToken myToken = tokenRepository.save(new VerificationToken(tokenString, user));
        return myToken.getToken();
    }

    public User authenticateVerificationToken(String token) throws VerificationTokenException {
        if (token == null) {
            throw new VerificationTokenException("Missing verification token");
        }

        Optional<VerificationToken> optionalVerificationToken = tokenRepository.findByToken(token);

        VerificationToken verificationToken =
                optionalVerificationToken.orElseThrow(() -> new VerificationTokenException("Invalid verification token"));

        if ((verificationToken.getExpiryDate().getTime() < Calendar.getInstance().getTime().getTime())) {
            throw new VerificationTokenException("Verification token expired");
        }

        return verificationToken.getUser();
    }
}
