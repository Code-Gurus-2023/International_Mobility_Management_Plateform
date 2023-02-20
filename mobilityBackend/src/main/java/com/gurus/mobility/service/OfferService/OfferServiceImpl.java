package com.gurus.mobility.service.OfferService;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class OfferServiceImpl implements IOfferService{
    @Autowired
    private IOfferRepository offerRepository;

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(Integer id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id " + id));
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }


    @Override
    public Offer updateOffer(Offer o) {
        offerRepository.save(o);
        return o;
    }

    @Override
    public void deleteOffer(Integer id) {
        offerRepository.deleteById(id);
    }

}