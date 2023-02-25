package com.gurus.mobility.service.AlertServices;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.alert.Target;
import com.gurus.mobility.repository.AlertRepositories.AlertRepository;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements IAlertService{
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private IOfferRepository iOfferRepository;
    @Override
    public void createAlert(Alert alert) {
        alertRepository.save(alert);
    }

    public void sendAlerts (){
        List<Offer> offerList= iOfferRepository.getLastFiveMinutesOffers();
        List<Alert> alertList= alertRepository.findAll();
        for (Offer offer:offerList) {
            for (Alert alert: alertList) {
                if((alert.getAlrtTarget().equals(Target.OFFER_UNIVERSITE)&&(alert.getUniversiteAlrt().equals(offer.getUser().getUserName())))){
                    //sendSMSToNumberPhone(alert.getAlrtPhoneNumber(), Message);
                }
            }
        }
    }
}
