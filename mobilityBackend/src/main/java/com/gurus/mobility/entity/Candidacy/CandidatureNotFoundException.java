package com.gurus.mobility.entity.Candidacy;


public class CandidatureNotFoundException extends RuntimeException {

    public CandidatureNotFoundException(Integer idCandidacy) {
        super("Candidature not found with id : " + idCandidacy);
    }

}

