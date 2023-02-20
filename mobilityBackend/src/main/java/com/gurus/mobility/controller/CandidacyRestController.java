package com.gurus.mobility.controller;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.service.CandidacyServices.ICandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/candidacy")
public class CandidacyRestController {
    @Autowired
    private ICandidacyService candidacyService;

    @GetMapping
    public List<Candidacy> getAllCandidacy() {
        return candidacyService.getAllCandidacy();
    }

    @GetMapping("/{id}")
    public Candidacy getCandidacyById(@PathVariable Integer id) {
        return candidacyService.getCandidacyById(id);
    }

    @PostMapping
    public ResponseEntity<Candidacy> createCandidacy(@Valid @RequestBody Candidacy candidacy) {
        Candidacy createdCandidacy = candidacyService.createCandidacy(candidacy);
        return new ResponseEntity<>(createdCandidacy, HttpStatus.CREATED);
    }
    @PutMapping("/update-candidacy")
    public Candidacy updateCandidacy(@RequestBody Candidacy candidacy) {
        return candidacyService.updateCandidacy(candidacy);
    }
}
