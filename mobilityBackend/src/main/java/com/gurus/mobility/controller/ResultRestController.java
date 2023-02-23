package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.service.CandidacyServices.ICandidacyService;
import com.gurus.mobility.service.CandidacyServices.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/result")
public class ResultRestController {

    @Autowired
    private IResultService resultService;

    @GetMapping("/getResult")
    public List<Result> getAllResult() {
        return resultService.getAllResult();
    }

    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Integer id) {
        return resultService.getResultById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Result> createResult(@Valid @RequestBody Result result) {
        Result createdResult = resultService.createResult(result);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Result updateResult(@PathVariable Integer id, @Valid @RequestBody Result resultDetails) {
        return resultService.updateResult(id, resultDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}