package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.service.CandidacyServices.ICandidacyService;
import com.gurus.mobility.service.CandidacyServices.IResultService;
import com.gurus.mobility.service.CandidacyServices.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/result")
public class ResultRestController {

    @Autowired
    private IResultService resultService;
    @Autowired
    private ResultServiceImpl resultServiceImpl;
    @Autowired
    public ResultRestController(IResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/getResult")
    public List<Result> getAllResult() {
        return resultService.getAllResult();
    }


    /*@GetMapping("/{id}")
    public Result getResultById(@PathVariable Integer id) {
        return resultService.getResultById(id);
    }*/

    /*@PostMapping("/create")
    public ResponseEntity<Result> createResult(@Valid @RequestBody Result result) {
        Result createdResult = resultServiceImpl.createResult(result);
       return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    } */

    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        Result savedResult = resultServiceImpl.save(result);
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Integer id) {
        Result result = resultServiceImpl.findById(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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

    @GetMapping("/export/excel")
    public void exportResultToExcel(HttpServletResponse response) {
        resultService.exportResultToExcel(response);
    }

    @GetMapping("/top-10")
    public List<Result> getTop10Resultats() {
        return resultService.findTop10ByOrderByNoteDesc();
    }



    @GetMapping
    public List<Result> getAllResults() {
        return resultServiceImpl.findAll();
    }

}