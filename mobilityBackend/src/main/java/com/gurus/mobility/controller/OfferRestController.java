package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.service.OfferService.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    @Autowired
    private IOfferService offerService;

    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Integer id) {
        return offerService.getOfferById(id);
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer) {
        Offer createdOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PutMapping("/modify-utilisateur")
    public Offer modifyUtilisateur(@RequestBody Offer o) {
        return offerService.updateOffer(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
