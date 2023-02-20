package com.gurus.mobility.service.CandidacyServices;

import com.gurus.mobility.entity.Candidacy;


import java.util.List;

public interface ICandidacyService {

        List<Candidacy> getAllCandidacy();
        Candidacy getCandidacyById(Integer id);
        Candidacy createCandidacy(Candidacy candidacy);
       Candidacy updateCandidacy(Candidacy candidacy);
        void deleteCandidacy(Integer id);
    }

