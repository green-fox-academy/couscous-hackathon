package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.webshop.security.VerificationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private VerificationToken verificationToken;

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        enabled = false;
    }
}