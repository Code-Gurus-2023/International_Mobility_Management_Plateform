package com.gurus.mobility.repository.OfferRepository;

import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentaireRepository extends JpaRepository<Commentaire,Integer> {
}
