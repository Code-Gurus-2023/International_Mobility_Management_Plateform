package com.gurus.mobility.entity.Offer;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    private String toEmail;
    private String subject;
    private String body;


}
