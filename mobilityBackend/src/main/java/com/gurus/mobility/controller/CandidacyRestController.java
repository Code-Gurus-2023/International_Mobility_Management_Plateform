package com.gurus.mobility.controller;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.service.CandidacyServices.ICandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/candidacy")
public class CandidacyRestController {
    @Autowired
    private ICandidacyService candidacyService;

    @GetMapping("/getCandidacy")
    public List<Candidacy> getAllCandidacy() {
        return candidacyService.getAllCandidacy();
    }

    @GetMapping("/{id}")
    public Candidacy getCandidacyById(@PathVariable Integer id) {
        return candidacyService.getCandidacyById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Candidacy> createCandidacy(@Valid @RequestBody Candidacy candidacy) {
        Candidacy createdCandidacy = candidacyService.createCandidacy(candidacy);
        return new ResponseEntity<>(createdCandidacy, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Candidacy updateCandidacy(@PathVariable Integer id, @Valid @RequestBody Candidacy candidacyDetails) {
        return candidacyService.updateCandidacy(id, candidacyDetails);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCandidacy(@PathVariable Integer id) {
        candidacyService.deleteCandidacy(id);
        return ResponseEntity.noContent().build();
    }
}
