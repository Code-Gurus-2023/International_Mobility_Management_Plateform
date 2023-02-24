package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.MailRequest;
import com.gurus.mobility.service.OfferService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class MailController {

    private final MailService emailService;

    @Autowired
    public MailController(MailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestParam String toEmail,
                                       @RequestParam String subject,
                                       @RequestParam String body) {
        try {
            emailService.sendEmail(toEmail, subject, body);
            return ResponseEntity.ok("Email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }
}

