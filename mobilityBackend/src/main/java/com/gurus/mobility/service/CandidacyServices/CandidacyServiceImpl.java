package com.gurus.mobility.service.CandidacyServices;

import com.gurus.mobility.entity.Candidacy.*;
import com.gurus.mobility.entity.Offer.Profil;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.UpdateCandidacyException;
import com.gurus.mobility.repository.Candidacy.ICandidacyRepository;
import com.gurus.mobility.repository.Candidacy.IResultRepository;
import com.gurus.mobility.repository.User.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CandidacyServiceImpl implements ICandidacyService {

    @Autowired
    private ICandidacyRepository candidacyRepository;
    @Autowired
    private IResultRepository resultRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Candidacy> getAllCandidacy() {
        return candidacyRepository.findAll();
    }

    @Override
    public List<Candidacy> getAllCandidacy2() {
        return candidacyRepository.findAll();
    }
    @Override
    public Candidacy getCandidacyById1(Integer id) {
        return candidacyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidacy not found with id " + id));
    }

    @Override
    public Candidacy createCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public Candidacy updateCandidacy(Integer id, Candidacy candidacyDetails) {
        Candidacy candidacy = getCandidacyById1(id);
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
        candidacy.setMoyenneGenerale(candidacyDetails.getMoyenneGenerale());
        candidacy.setScoree(candidacyDetails.getScoree());

        return candidacyRepository.save(candidacy);
    }

    @Override
    public void deleteCandidacy(Integer id) {
        candidacyRepository.deleteById(id);
    }


    public List<Candidacy> getCandidacyByNom(String firstName) {
        return candidacyRepository.findByNom(firstName);
    }

    @Override
    public List<Candidacy> trierParDate() {
        List<Candidacy> candidacy = candidacyRepository.findAll();
        Collections.sort(candidacy, new Comparator<Candidacy>() {
            @Override
            public int compare(Candidacy c1, Candidacy c2) {
                return c1.getDateCandidacy().compareTo(c2.getDateCandidacy());
            }
        });

        return candidacy;
    }

    @Override
    public void archiveCandidature(Integer id) {
        Candidacy candidature = getCandidacyById1(id);
        candidacyRepository.delete(candidature);

        try {
            FileWriter fileWriter = new FileWriter("C:/Spring Boot/Candidatures_archivées.txt", true);
            fileWriter.write(candidature.getIdCandidacy() + "," + candidature.getFirstName() + "," +
                    candidature.getLastName() + "," + candidature.getEmail() + "," +
                    candidature.getTelephoneNumber() + candidature.getPostalCode() + "," + candidature.getDateCandidacy() + "," + candidature.getAddress() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Candidacy> getAllCandidatures(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        return candidacyRepository.findAll(pageable);
    }

    @Override
    public Map<DomainCandidacy, Long> getNombreCandidaturesParDomaine() {
        List<Candidacy> candidatures = candidacyRepository.findAll();
        return candidatures.stream()
                .collect(Collectors.groupingBy(Candidacy::getDomainCandidacy, Collectors.counting()));
    }

    @Override
    public Candidacy getCandidatureById(Integer idCandidacy) {
        Optional<Candidacy> candidatureOptional = candidacyRepository.findById(idCandidacy);
        if (candidatureOptional.isPresent()) {
            return candidatureOptional.get();
        } else {
            throw new CandidatureNotFoundException(idCandidacy);
        }
    }

    @Override
    public Candidacy updateCandidacyStatus(Integer idCandidacy) {
        Candidacy candidacy = candidacyRepository.findById(idCandidacy)
                .orElseThrow(() -> new RuntimeException("Candidacy not found with id " + idCandidacy));

        if (candidacy.getScoree() > 20 && candidacy.getMoyenneGenerale() > 16 && candidacy.getProfil() == Profil.ETUDIANT) {
            candidacy.setStatusCandidacy(StatusCandidacy.EN_ATTENTE);
        } else {
            candidacy.setStatusCandidacy(StatusCandidacy.REFUSEE);
        }

        return candidacyRepository.save(candidacy);
    }

    @Override
    public List<Candidacy> getCandidatesByStatus(StatusCandidacy statusCandidacy) {
        return candidacyRepository.findByStatusCandidacy(statusCandidacy);
    }

    @Override
    public Candidacy updateCandidatureStatus(Integer idCandidacy) {
        Candidacy candidature = candidacyRepository.findById(idCandidacy)
                .orElseThrow(() -> new RuntimeException("Candidacy not found with id " + idCandidacy));

        if (candidature.getStatusCandidacy() == StatusCandidacy.EN_ATTENTE && candidature.getDisponibilite() == Disponibilite.IMMEDIATE && candidature.getProfil() == Profil.ETUDIANT) {
            candidature.setStatusCandidacy(StatusCandidacy.ACCEPTEE);
            return candidacyRepository.save(candidature);
        } else {
            throw new CandidatureNotEligibleException("Candidature not eligible for automatic acceptance");
        }
    }

    @Override
    public void accepterOuRefuserCandidature(Integer idCandidacy) {
        Candidacy candidature = getCandidatureById(idCandidacy);

        if (candidature.getProfil() == Profil.ENSEIGNANT && candidature.getAnneeExperience() > 3) {
            candidature.setStatusCandidacy(StatusCandidacy.EN_ATTENTE);
        } else {
            candidature.setStatusCandidacy(StatusCandidacy.REFUSEE);
        }

        saveCandidature(candidature);
    }

    @Override
    public Candidacy saveCandidature(Candidacy candidature) {
        return candidacyRepository.save(candidature);
    }

    @Override
    public List<Candidacy> getCandidacyByUser(Long userId) {
        return userRepository.findById(userId).get().getCandidacies().stream().toList();
    }

    @Override
    public void createCandidacy(Candidacy candidacy, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UpdateCandidacyException("object not found with id =" + userId));
        user.getCandidacies().add(candidacy);
        //candidacy.setAlertCreationDate(LocalDateTime.now());
        candidacyRepository.save(candidacy);
        userRepository.save(user);
    }


}





