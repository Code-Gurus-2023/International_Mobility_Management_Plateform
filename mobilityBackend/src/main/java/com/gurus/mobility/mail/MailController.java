package com.gurus.mobility.mail;

import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.service.User.UserServiceImpl;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
@Api(value = "Rest Controller: Mail")
public class MailController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private SendMailService sendMailService;
    @PostMapping("/sendEmailToUser")
    public ResponseEntity<Boolean> sendEmail(@RequestBody RequestMail mail) {
        User user= userService.getUserByIdentifiant(mail.getIdentifiantUser());

        if (user == null) {
            String errorMessage = "The selected user for sending email is not found in the database";
            LOGGER.info(errorMessage);
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        } else if (user != null && user.getEmail() == null) {
            String errorMessage = "No existing email for the selected user for sending email to";
            LOGGER.info(errorMessage);
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
        try {

            this.sendMailService.sendMail(mail);
        } catch (MailException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
