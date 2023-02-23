package com.gurus.mobility.service.OfferService;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Comparator;
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
    public Offer updateOffer(Integer id, Offer offerDetails) {
        Offer offer = getOfferById(id);
        offer.setTitle(offerDetails.getTitle());
        offer.setImage(offerDetails.getImage());
        offer.setDateOffre(offerDetails.getDateOffre());
        offer.setNbreCandidats(offerDetails.getNbreCandidats());
        offer.setProfil(offerDetails.getProfil());
        offer.setDestination(offerDetails.getDestination());
        offer.setDuration(offerDetails.getDuration());
        offer.setConditions(offerDetails.getConditions());
        offer.setAdvantages(offerDetails.getAdvantages());
        return offerRepository.save(offer);
    }


    @Override
    public void deleteOffer(Integer id) {
        offerRepository.deleteById(id);
    }

    public List<Offer> getOffersByTitle(String title) {
        return offerRepository.findByTitle(title);
    }

    @Override
    public List<Offer> trierParDate() {
        List<Offer> offers = offerRepository.findAll();
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer c1, Offer c2) {
                return c1.getDateOffre().compareTo(c2.getDateOffre());
            }
        });
        return offers;
    }
}