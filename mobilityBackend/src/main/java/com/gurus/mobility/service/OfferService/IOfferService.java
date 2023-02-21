package com.gurus.mobility.service.OfferService;

import com.gurus.mobility.entity.Offer.Offer;

import java.util.List;

public interface IOfferService {
    List<Offer> getAllOffers();
    Offer getOfferById(Integer id);
    Offer createOffer(Offer offer);
    Offer updateOffer(Offer o);
    void deleteOffer(Integer id);
}
