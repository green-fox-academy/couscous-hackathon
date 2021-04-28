package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.security.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
