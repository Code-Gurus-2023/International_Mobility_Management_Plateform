package com.gurus.mobility.mail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestMail {

    private String sendFrom;

    private String sendTo;

    private String subject;

    private String content;

    private String identifiantUser;

}


