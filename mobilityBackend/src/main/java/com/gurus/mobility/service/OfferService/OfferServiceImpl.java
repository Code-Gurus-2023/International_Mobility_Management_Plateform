package com.gurus.mobility.service.OfferService;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import com.gurus.mobility.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OfferServiceImpl {

    @Autowired
    private IOfferRepository iOfferRepository;
    @Autowired
    private UserRepository userRepository;
    public void createOffer(Offer of, Long idUser){
        of.setOfferCreationDate(LocalDateTime.now());
        of.setUser(userRepository.findById(idUser).get());
        iOfferRepository.save(of);
    }
}
