package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.DomainCandidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.entity.Candidacy.StatusCandidacy;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.Candidacy.ICandidacyRepository;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.CandidacyServices.CandidacyServiceImpl;
import com.gurus.mobility.service.CandidacyServices.ICandidacyService;
import com.gurus.mobility.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidacy")
public class CandidacyRestController {
    public User authorisation(){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
    }
    @Autowired
    private IUserService iUserService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private ICandidacyService candidacyService;
    @Autowired
    private ICandidacyRepository candidacyRepository;

    @Autowired
    private CandidacyServiceImpl candidacyServiceImpl;


   //http://localhost:8081/espritmobility/api/candidacy/getCandidacy
    @GetMapping("/getCandidacy")
    public List<Candidacy> getAllCandidacy() {
        return candidacyService.getAllCandidacy();
    }


    /*@GetMapping("/{id}")
    public Candidacy getCandidacyById(@PathVariable Integer id) {
        return candidacyService.getCandidacyById(id);
    }*/


    //http://localhost:8081/espritmobility/api/candidacy/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Candidacy> getCandidatureById(@PathVariable Integer idCandidacy) {
        Optional<Candidacy> candidatureOptional =candidacyRepository.findById(idCandidacy);
        if (candidatureOptional.isPresent()) {
            Candidacy candidature = candidatureOptional.get();
            return ResponseEntity.ok(candidature);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //http://localhost:8081/espritmobility/api/candidacy/create
    @PostMapping("/create")
    public ResponseEntity<Candidacy> createCandidacy(@Valid @RequestBody Candidacy candidacy) {
        Candidacy createdCandidacy = candidacyService.createCandidacy(candidacy);
        return new ResponseEntity<>(createdCandidacy, HttpStatus.CREATED);
    }


    //http://localhost:8081/espritmobility/api/candidacy/{id}
    @PutMapping("/{id}")
    public Candidacy updateCandidacy(@PathVariable Integer id, @Valid @RequestBody Candidacy candidacyDetails) {
        return candidacyService.updateCandidacy(id, candidacyDetails);
    }


    //http://localhost:8081/espritmobility/api/candidacy/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidacy(@PathVariable Integer id) {
        candidacyService.deleteCandidacy(id);
        return ResponseEntity.noContent().build();
    }


    //http://localhost:8081/espritmobility/api/candidacy/search?firstName=ikram
    @GetMapping("/search")
    public ResponseEntity<List<Candidacy>> searchCandidacy(@RequestParam(value = "firstName") String firstName) {
        List<Candidacy> candidacy = candidacyService.getCandidacyByNom(firstName);
        return new ResponseEntity<>(candidacy, HttpStatus.OK);
    }

    //http://localhost:8081/espritmobility/api/candidacy/tri/date
    @GetMapping("/tri/date")
    public ResponseEntity<List<Candidacy>> trierParDate() {
        List<Candidacy> candidacy = candidacyService.trierParDate();
        return new ResponseEntity<>(candidacy, HttpStatus.OK);
    }

    //http://localhost:8081/espritmobility/api/candidacy/{id}/archive
    @PostMapping("/{id}/archive")
    public void archiveCandidature(@PathVariable Integer id) {
        candidacyService.archiveCandidature(id);
    }


    //http://localhost:8081/espritmobility/api/candidacy/pagination?pageNumber=0&pageSize=10&sortBy=idCandidacy
    @GetMapping("/pagination")
    public ResponseEntity<Page<Candidacy>> getAllCandidatures(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Candidacy> result = candidacyService.getAllCandidatures(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }


    //http://localhost:8081/espritmobility/api/candidacy/statistiques
    @GetMapping("/statistiques")
    public Map<DomainCandidacy, Long> getNombreCandidaturesParDomaine() {
        return candidacyService.getNombreCandidaturesParDomaine();
    }


    //Selection Candidature Entudiant (ESPRIT)
    //http://localhost:8081/espritmobility/api/candidacy/update/{idCandidacy}/status
    @PutMapping("/update/{idCandidacy}/status")
    public Candidacy updateCandidacyStatus(@PathVariable("idCandidacy") Integer idCandidacy) {
        return candidacyService.updateCandidacyStatus(idCandidacy);
    }

    //http://localhost:8081/espritmobility/api/candidacy/pending
    @GetMapping("/pending")
    public List<Candidacy> getPendingCandidates() {
        return candidacyService.getCandidatesByStatus(StatusCandidacy.EN_ATTENTE);
    }


   //Selection Candidature Etudiant (UNIVERSITE)
   //http://localhost:8081/espritmobility/api/candidacy/put/{idCandidacy}
    @PutMapping("/put/{idCandidacy}")
    public Candidacy updateCandidatureStatus(@PathVariable Integer idCandidacy) {
        return candidacyService.updateCandidatureStatus(idCandidacy);
    }

    //http://localhost:8081/espritmobility/api/candidacy/save
    @PostMapping("/save")
    public Candidacy saveCandidature(@RequestBody Candidacy candidature) {
        return candidacyService.saveCandidature(candidature);
    }

    //Selection Candidature Enseignant
    //http://localhost:8081/espritmobility/api/candidacy/update1/{idCandidacy}
    @PutMapping("/update1/{idCandidacy}")
    public void accepterOuRefuserCandidature(@PathVariable Integer idCandidacy) {
        candidacyService.accepterOuRefuserCandidature(idCandidacy);
    }

}
