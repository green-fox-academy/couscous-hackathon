package com.greenfoxacademy.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    private JavaMailSender emailSender;

    @Autowired
    public EmailService(@Qualifier("getJavaMailSender") JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendRegistrationMessage(String to, String url, String token)
            throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("couscousfoxhospital.noreply@gmail.com");
        helper.setTo(to);
        helper.setSubject("Registration Confirmation");
        helper.setText("<html><body>" +
                "<p>Thank you for your registration!<p><br>" +
                "<p>Please confirm it by clicking on the following link:>" +
                "<a href=\"" + url + "registration-confirm?token=" + token + "\">" + token + "</a>" +
                "<br><p>Couscous Fox Hospital<p>" +
                "</body></html>", true);

        emailSender.send(message);
    }



}