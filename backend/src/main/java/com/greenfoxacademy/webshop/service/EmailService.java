package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.model.Purchase;
import com.greenfoxacademy.webshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Component
public class EmailService {

    private JavaMailSender emailSender;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(
            @Qualifier("getJavaMailSender") JavaMailSender emailSender,
            SpringTemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    public void sendRegistrationMessage(String to, String url, String token)
            throws MessagingException {

        String subject = "Registration Confirmation";
        String htmlBody = "<html><body>" +
                "<p>Thank you for your registration!<p>" +
                "<p>Please confirm it by clicking on the following link: >" +
                "<a href=\"" + url + "/registration-confirm?token=" + token + "\">" + token + "</a>" +
                "<br><p>Couscous Fox Hospital<p>" +
                "</body></html>";

        sendHtmlMessage(to, subject, htmlBody);
    }

    public void sendCheckoutMessage(User user, Purchase purchase) throws MessagingException {
        Context checkoutContext = new Context();
        checkoutContext.setVariable("username", user.getUsername());
        checkoutContext.setVariable("purchase", purchase);

        String htmlBody = templateEngine.process("checkout-template.html", checkoutContext);

        sendHtmlMessage(user.getEmail(), "Couscous Fox Hospital contribution", htmlBody);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("couscousfoxhospital.noreply@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }

}