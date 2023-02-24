package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.service.OfferService.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    @Autowired
    private IOfferService offerService;

    @GetMapping("/getOffers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Integer id) {
        return offerService.getOfferById(id);
    }

    @PostMapping("/createOffer")
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer) {
        Offer createdOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Offer updateOffer(@PathVariable Integer id, @Valid @RequestBody Offer offerDetails) {
        return offerService.updateOffer(id, offerDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Offer>> searchOffers(@RequestParam(value = "title") String title) {
        List<Offer> offers = offerService.getOffersByTitle(title);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/tri/date")
    public ResponseEntity<List<Offer>> trierParDate() {
        List<Offer> offers = offerService.trierParDate();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @PostMapping("/{id}/archive")
    public void archiveCandidature(@PathVariable Integer id) {
        offerService.archiveOffer(id);
    }

    @GetMapping("/export/excel")
    public void exportOffersToExcel(HttpServletResponse response) {
        offerService.exportOffersToExcel(response);
    }
}