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
    public List<Candidacy> getAllCandidacy() {
        return candidacyRepository.findAll();
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
    public Candidacy updateCandidacy(Candidacy candidacy) {
        candidacyRepository.save(candidacy);
        return candidacy;
    }

    @Override
    public void deleteCandidacy(Integer id) {
        candidacyRepository.deleteById(id);
    }


}
