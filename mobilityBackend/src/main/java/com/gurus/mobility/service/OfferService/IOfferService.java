package com.gurus.mobility.service.OfferService;

import com.gurus.mobility.entity.Offer.Destination;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.user.ERole;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IOfferService {
    List<Offer> getAllOffers();
    Offer getOfferById(Integer id);
    Offer createOffer(Offer offer);
    Offer updateOffer(Integer id, Offer offerDetails);
    void deleteOffer(Integer id);
    List<Offer> getOffersByTitle(String title);
    List<Offer> trierParDate();
    void archiveOffer(Integer id);
    void exportOffersToExcel(HttpServletResponse response);
    Page<Offer> paginationOffers(int pageNumber, int pageSize, String sortBy);
    List<Offer> getOffresDateSuperieur(LocalDate date);
    Map<Destination, Long> getNombreOffresParDestination();
    List<Offer> getOffresEnseignant();
    List<Offer> getOffresEtudiant();









}
