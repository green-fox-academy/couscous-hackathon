package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.security.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
}
