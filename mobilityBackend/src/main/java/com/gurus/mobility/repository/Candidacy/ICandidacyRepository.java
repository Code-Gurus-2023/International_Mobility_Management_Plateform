package com.gurus.mobility.repository.Candidacy;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.StatusCandidacy;
import com.gurus.mobility.entity.Offer.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICandidacyRepository extends JpaRepository<Candidacy, Integer> {
    @Query("SELECT c FROM Candidacy c WHERE c.firstName = :firstName")
    List<Candidacy> findByNom(@Param("firstName") String firstName);

    List<Candidacy> findByStatusCandidacy(StatusCandidacy statusCandidacy);


}

