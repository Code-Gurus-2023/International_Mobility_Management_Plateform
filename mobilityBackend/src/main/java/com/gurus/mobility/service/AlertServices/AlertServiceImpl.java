package com.gurus.mobility.service.AlertServices;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.alert.Kind;
import com.gurus.mobility.entity.alert.Target;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.UpdateClaimException;
import com.gurus.mobility.repository.AlertRepositories.AlertRepository;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import com.gurus.mobility.repository.User.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class AlertServiceImpl implements IAlertService{
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private IOfferRepository iOfferRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailContentBuilder mailContentBuilder;
    @Override
    public void createAlert(Alert alert, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UpdateClaimException("object not found with id =" + userId));
        user.getAlerts().add(alert);
        alert.setAlertCreationDate(LocalDateTime.now());
        alertRepository.save(alert);
        userRepository.save(user);
    }

    public void sendEmail(String to,String subject, String templateName, Map<String, Object> variables) throws MessagingException{
        mailContentBuilder.build(to,subject,templateName,variables);
    }

    @Override
    @Scheduled(fixedRate = 300000)
    public void sendAlerts (){
        List<Offer> offerList= iOfferRepository.findByOfferCreationDateAfter(LocalDateTime.now().minusMinutes(5));
        Twilio.init("AC0dcec74e1defc2c4d59b1d4d92bb77c7","7aa1128e771ef3e6b9a8d1a91d584e0a");
        PhoneNumber from= new PhoneNumber("+1 276 296 5689");
        SimpleMailMessage message= new SimpleMailMessage();
        List<Alert> alertList= alertRepository.findAll();
        for (Offer offer:offerList) {
            for (Alert alert: alertList) {
                if(alert.getAlrtKind().equals(Kind.SMS)){
                    switch (alert.getAlrtTarget()){
                        case OFFER_UNIVERSITE:
                            if(alert.getUniversiteAlrt().equals(offer.getUser().getUserName())){
                                System.out.println("sending alert ...");
                                String msg="Good Morning,\nWe inform you that university "+offer.getUser().getUserName().toUpperCase()+" has published a mobility offer.\nTo apply for this offer, please access the ESPRIT MOBILITY platform.\n" +
                                        "This message was sent automatically.Thank you for not responding";
                                PhoneNumber userPhone= new PhoneNumber("+216 "+alert.getAlrtPhoneNumber());
                                Message.creator(userPhone,from,msg).create();
                            }
                            break;
                        case REGION:
                            if(alert.getRegionAlert().equals(offer.getUser().getLocation())){
                                System.out.println("sending alert ...");
                                PhoneNumber userPhone= new PhoneNumber("+216 "+alert.getAlrtPhoneNumber());
                                String msg="Good Morning,\nWe inform you that university "+offer.getUser().getUserName().toUpperCase()+" in the region "+offer.getUser().getLocation()+" has published a mobility offer.\nTo apply for this offer, please access the ESPRIT MOBILITY platform.\n" +
                                        "This message was sent automatically.Thank you for not responding";
                                Message.creator(userPhone,from,msg).create();
                            }
                            break;
                        case COUNTRY:
                            if (alert.getCoutryAlert().equals(offer.getUser().getCountry())){
                                System.out.println("sending alert ...");
                                String msg="Good Morning,\nWe inform you that university "+offer.getUser().getUserName().toUpperCase()+" in the country "+offer.getUser().getCountry()+" has published a mobility offer.\nTo apply for this offer, please access the ESPRIT MOBILITY platform.\n" +
                                        "This message was sent automatically.Thank you for not responding";
                                PhoneNumber userPhone= new PhoneNumber("+216 "+alert.getAlrtPhoneNumber());
                                Message.creator(userPhone,from,msg).create();
                            }
                            break;
                    }

                }
                if(alert.getAlrtKind().equals(Kind.EMAIL)) {
                    switch (alert.getAlrtTarget()) {
                        case OFFER_UNIVERSITE:
                            if (alert.getUniversiteAlrt().equals(offer.getUser().getUserName())) {
                                System.out.println("sending alert ...");
                                String msg="We inform you that university "+offer.getUser().getUserName().toUpperCase()+" has published a mobility offer.\nTo apply for this offer, please access the ESPRIT MOBILITY platform.\n" +
                                        "This message was sent automatically.Thank you for not responding";
                                Map<String, Object> variables = new HashMap<>();
                                variables.put("subject", "Welcome to my website!");
                                variables.put("message", msg);
                                try{
                                    sendEmail(alert.getAlrtEmail(),"ESPRIT OFFER UNIVERSITY ALERT","mailTemplate",variables);
                                }catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                            break;
                        case REGION:
                            if (alert.getRegionAlert().equals(offer.getUser().getLocation())){
                                System.out.println("sending alert ...");
                                String msg="We inform you that university "+offer.getUser().getUserName().toUpperCase()+" in the region "+offer.getUser().getLocation().toUpperCase()+ " has published a mobility offer.\nTo apply for this offer, please access the ESPRIT MOBILITY platform.\n" +
                                        "This message was sent automatically.Thank you for not responding";
                                Map<String, Object> variables = new HashMap<>();
                                variables.put("subject", "Welcome to my website!");
                                variables.put("message", msg);
                                try{
                                    sendEmail(alert.getAlrtEmail(),"ESPRIT REGION UNIVERSITY ALERT","mailTemplate",variables);
                                }catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                            break;
                        case COUNTRY:
                            if (alert.getCoutryAlert().equals(offer.getUser().getCountry())) {
                                System.out.println("sending alert ...");
                                String msg="We inform you that university "+offer.getUser().getUserName().toUpperCase()+" in the "+offer.getUser().getCountry().toUpperCase()+ " country has published a mobility offer.\nTo apply for this offer, please access the ESPRIT MOBILITY platform.\n" +
                                        "This message was sent automatically.Thank you for not responding";
                                Map<String, Object> variables = new HashMap<>();
                                variables.put("subject", "Welcome to my website!");
                                variables.put("message", msg);
                                try{
                                    sendEmail(alert.getAlrtEmail(),"ESPRIT COUNTRY UNIVERSITY ALERT","mailTemplate",variables);
                                }catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                            break;
                    }
                }
            }
        }
    }


    @Override
    public List<Alert> getAlertsByUser(Long userId){
        return userRepository.findById(userId).get().getAlerts().stream().toList();
    }

    @Override
    public List<Alert> getAllAlerts(){
        return alertRepository.findAll();
    }

    @Override
    public void updateAlert(Long id, Alert alert){
        Alert a= alertRepository.findById(id).get();
        a.setAlertModificationDate(LocalDateTime.now());
        a.setAlrtPhoneNumber(alert.getAlrtPhoneNumber());
        a.setAlrtEmail(alert.getAlrtEmail());
        alertRepository.save(a);
    }

    @Override
    public void deleteAlert(Alert alert){
        alertRepository.delete(alert);
    }

    public void fixedRateMethod() {
        List<Offer> lf=iOfferRepository.findByOfferCreationDateAfter(LocalDateTime.now().minusMinutes(5));
        //List<Offer> lf= iOfferRepository.findAll();
        PhoneNumber to= new PhoneNumber("+216 53310166");
        PhoneNumber from= new PhoneNumber("+1 276 296 5689");
        if(!lf.isEmpty()){
            for (Offer off:lf) {
                System.out.println("test test est");
                Twilio.init("AC0dcec74e1defc2c4d59b1d4d92bb77c7","9b5bf4568d65ed7a82b6e93c23bbf687");
                Message.creator(to,from,"this is a test message form spring boot app").create();
            }
        }else {
            System.out.println("la liste est vide");
        }
    }
}
