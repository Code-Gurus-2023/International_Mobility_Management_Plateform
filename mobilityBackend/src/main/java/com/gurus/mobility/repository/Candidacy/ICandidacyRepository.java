package com.gurus.mobility.repository.Candidacy;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidacyRepository extends JpaRepository<Candidacy, Integer> {
}

