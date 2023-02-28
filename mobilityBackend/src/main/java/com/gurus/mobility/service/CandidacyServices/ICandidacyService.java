package com.gurus.mobility.service.CandidacyServices;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import org.springframework.data.domain.Page;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ICandidacyService {

        List<Candidacy> getAllCandidacy();
        Candidacy getCandidacyById(Integer id);
        Candidacy createCandidacy(Candidacy candidacy);
    Candidacy updateCandidacy(Integer id, Candidacy candidacyDetails);
        void deleteCandidacy(Integer id);
    List<Candidacy> getCandidacyByNom(String firstName);

    List<Candidacy> trierParDate();

    void archiveCandidature(Integer id);

    Page<Candidacy> getAllCandidatures(int pageNumber, int pageSize, String sortBy);

    }

