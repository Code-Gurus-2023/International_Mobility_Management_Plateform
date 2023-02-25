package com.gurus.mobility.repository.OfferRepository;

import com.gurus.mobility.entity.Offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface IOfferRepository extends JpaRepository<Offer, Long> {
    LocalDate now = LocalDate.now();
    @Query(value = "SELECT f from Offer f where f.offerCreationDate>= current_time-5")
    public List<Offer> getLastFiveMinutesOffers();
}
