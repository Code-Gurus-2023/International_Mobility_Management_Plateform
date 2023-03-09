package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.CandidacyServices.ICandidacyService;
import com.gurus.mobility.service.CandidacyServices.IResultService;
import com.gurus.mobility.service.CandidacyServices.ResultServiceImpl;
import com.gurus.mobility.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/result")
public class ResultRestController {

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
    private IResultService resultService;
    @Autowired
    private ResultServiceImpl resultServiceImpl;
    @Autowired
    public ResultRestController(IResultService resultService) {
        this.resultService = resultService;
    }


    //http://localhost:8081/espritmobility/api/result/getResult
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


    //http://localhost:8081/espritmobility/api/result
    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        Result savedResult = resultServiceImpl.save(result);
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    //http://localhost:8081/espritmobility/api/result/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Integer id) {
        Result result = resultServiceImpl.findById(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //http://localhost:8081/espritmobility/api/result/{id}
    @PutMapping("/{id}")
    public Result updateResult(@PathVariable Integer id, @Valid @RequestBody Result resultDetails) {
        return resultService.updateResult(id, resultDetails);
    }

    //http://localhost:8081/espritmobility/api/result/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }

    //http://localhost:8081/espritmobility/api/result/export/excel
    @GetMapping("/export/excel")
    public void exportResultToExcel(HttpServletResponse response) {
        resultService.exportResultToExcel(response);
    }

    //http://localhost:8081/espritmobility/api/result/top-10
    @GetMapping("/top-10")
    public List<Result> getTop10Resultats() {
        return resultService.findTop10ByOrderByNoteDesc();
    }


    //http://localhost:8081/espritmobility/api/result
    @GetMapping
    public List<Result> getAllResults() {
        return resultServiceImpl.findAll();
    }


    //http://localhost:8081/espritmobility/api/result/{id}/archive
    @PostMapping("/{id}/archive")
    public void archiveResult(@PathVariable Integer id) {
        resultService.archiveResult(id);
    }

}