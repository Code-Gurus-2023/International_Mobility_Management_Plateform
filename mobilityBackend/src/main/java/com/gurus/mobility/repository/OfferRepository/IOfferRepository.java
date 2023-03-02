package com.gurus.mobility.repository.OfferRepository;

import com.gurus.mobility.entity.Offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOfferRepository extends JpaRepository<Offer,Integer> {
    @Query("SELECT c FROM Offer c WHERE c.title = :title")
    List<Offer> findByTitle(@Param("title") String title);

    @Query(value = "SELECT o FROM Offer o WHERE o.dateOffre > :date")
    List<Offer> getOffresDateSuperieur(LocalDate date );


}
