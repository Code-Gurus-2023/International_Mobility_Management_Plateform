package com.gurus.mobility.service.CandidacyServices;

import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.repository.Candidacy.ICandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
@Slf4j
@Service
public class CandidacyServiceImpl implements ICandidacyService {

    @Autowired
    private ICandidacyRepository candidacyRepository;

    @Override
    public List<Candidacy> getAllCandidacy() {return candidacyRepository.findAll();
    }

    @Override
    public Candidacy getCandidacyById(Integer id) {
        return candidacyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidacy not found with id " + id));
    }

    @Override
    public Candidacy createCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public Candidacy updateCandidacy(Integer id, Candidacy candidacyDetails) {
        Candidacy candidacy = getCandidacyById(id);
        candidacy.setCv(candidacyDetails.getCv());
        candidacy.setCoverLetter(candidacyDetails.getCoverLetter());
        candidacy.setFirstName(candidacyDetails.getFirstName());
        candidacy.setLastName(candidacyDetails.getLastName());
        candidacy.setEmail(candidacyDetails.getEmail());
        candidacy.setDateCandidacy(candidacyDetails.getDateCandidacy());
        candidacy.setTelephoneNumber(candidacyDetails.getTelephoneNumber());
        candidacy.setAddress(candidacyDetails.getAddress());
        candidacy.setPostalCode(candidacyDetails.getPostalCode());
        candidacy.setDomainCandidacy(candidacyDetails.getDomainCandidacy());
        candidacy.setStatusCandidacy(candidacyDetails.getStatusCandidacy());
        candidacy.setDisponibilite(candidacyDetails.getDisponibilite());
        candidacy.setSelectionne(candidacyDetails.getSelectionne());
        candidacy.setArchive(candidacyDetails.getArchive());
        candidacy.setCoverLetter(candidacyDetails.getCoverLetter());

        return candidacyRepository.save(candidacy);
    }

    @Override
    public void deleteCandidacy(Integer id) {
        candidacyRepository.deleteById(id);
    }


}
