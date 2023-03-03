package com.gurus.mobility.repository.Candidacy;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.StatusCandidacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

@Repository
public interface ICandidacyRepository extends JpaRepository<Candidacy, Integer> {
    @Query("SELECT c FROM Candidacy c WHERE c.firstName = :firstName")
    List<Candidacy> findByNom(@Param("firstName") String firstName);


    /* List<Candidacy> findByStatut(StatusCandidacy statutsCandidacy);

    List<Candidacy> findByStatutNot(StatusCandidacy statutsCandidacy);*/



}

