package com.gurus.mobility.service.CandidacyServices;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.DomainCandidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.entity.Candidacy.StatusCandidacy;
import com.gurus.mobility.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Map;

public interface ICandidacyService {

    List<Candidacy> getAllCandidacy();
    Candidacy getCandidacyById1(Integer id);
    Candidacy createCandidacy(Candidacy candidacy);
    Candidacy updateCandidacy(Integer id, Candidacy candidacyDetails);
    void deleteCandidacy(Integer id);
    List<Candidacy> getCandidacyByNom(String firstName);

    List<Candidacy> trierParDate();

    void archiveCandidature(Integer id);

    Page<Candidacy> getAllCandidatures(int pageNumber, int pageSize, String sortBy);

    Map<DomainCandidacy, Long> getNombreCandidaturesParDomaine();

    Candidacy getCandidatureById(Integer idCandidacy);

    Candidacy updateCandidacyStatus(Integer idCandidacy);

    List<Candidacy> getCandidatesByStatus(StatusCandidacy statusCandidacy);

    Candidacy updateCandidatureStatus(Integer idCandidacy);

    void accepterOuRefuserCandidature(Integer idCandidacy);
    Candidacy saveCandidature(Candidacy candidature);
    public List<Candidacy> getCandidacyByUser(Long userId);

    void createCandidacy(Candidacy candidacy, Long userId);


}
