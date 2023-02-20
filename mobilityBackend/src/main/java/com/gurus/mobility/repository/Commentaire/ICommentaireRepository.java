package com.gurus.mobility.repository.Commentaire;

import com.gurus.mobility.entity.Offer.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentaireRepository extends JpaRepository<Commentaire,Integer> {
}
