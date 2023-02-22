package com.gurus.mobility.service.CandidacyServices;


import com.gurus.mobility.entity.Candidacy.Candidacy;


import java.util.List;


public interface ICandidacyService {

        List<Candidacy> getAllCandidacy();
        Candidacy getCandidacyById(Integer id);
        Candidacy createCandidacy(Candidacy candidacy);
    Candidacy updateCandidacy(Integer id, Candidacy candidacyDetails);
        void deleteCandidacy(Integer id);
    }

