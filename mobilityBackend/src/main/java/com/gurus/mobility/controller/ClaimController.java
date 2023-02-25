package com.gurus.mobility.controller;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.entity.claim.Response;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.ClaimException;
import com.gurus.mobility.exception.UpdateClaimException;
import com.gurus.mobility.repository.Candidacy.ICandidacyRepository;
import com.gurus.mobility.service.ClaimServices.IClaimService;
import com.gurus.mobility.service.ClaimServices.IResponseService;
import com.gurus.mobility.service.IUserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Claim/")
public class ClaimController {
    @Autowired
    private IClaimService iClaimService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IResponseService iResponseService;
    //simple user can create new claim
    //
    @PostMapping("{idUser}")
    public ResponseEntity createClaim (@RequestBody Claim claim, @PathVariable("idUser")int idUser){
        iClaimService.createClaim(claim,idUser);
        return new ResponseEntity<>("claim sended successefully", HttpStatus.OK);
    }
    //simple user can delete claim that was created before
    @PutMapping("{idClaim}")
    public ResponseEntity deleteClaim(@PathVariable("idClaim")Long idClaim){
        iClaimService.archiveClaim(idClaim);
        return new ResponseEntity<>("claim deleted successefully", HttpStatus.OK);
    }
    // admin user can access all claims, even they was archived by simple users
    @GetMapping("allClaims/{idUser}")
    public ResponseEntity<List<Claim>> getAllClaims(@PathVariable("idUser")int idUser){
        if(!iUserService.findById(idUser).getRoles().contains(ERole.ROLE_ADMIN))
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iClaimService.getAllClaims(),HttpStatus.OK);
    }
    //simple user can access their active claims
    @GetMapping("myClaims/{idUser}")
    public ResponseEntity<List<Claim>> getClaimsByUser(@PathVariable("idUser")int idUser){
        return new ResponseEntity<>(iClaimService.getUserClaims(idUser),HttpStatus.OK);
    }

    //admin user access all active claims to respond the claims
    @GetMapping("{idUser}")
    public ResponseEntity<List<Claim>> getActiveClaims(@PathVariable("idUser")int idUser){
        if(!iUserService.findById(idUser).getRoles().contains(ERole.ROLE_ADMIN))
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iClaimService.getActiveClaims(),HttpStatus.OK);
    }

    //admin user can get users sorted by number of claims
    @GetMapping("sortUsers/{idUser}")
    public ResponseEntity<List<User>> getUsersByClaims(@PathVariable("idUser")int idUser){
        if(!iUserService.findById(idUser).getRoles().contains(ERole.ROLE_ADMIN))
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iClaimService.sortUsersByClaimsNumber(),HttpStatus.OK);
    }

    //user admin respond to specific claim
    @PostMapping("responseclaim/{idClaim}/{idUser}")
    public ResponseEntity respondClaim(@RequestBody Response response, @PathVariable("idClaim")Long idClaim, @PathVariable("idUser")int idUser){
        if(!iUserService.findById(idUser).getRoles().contains(ERole.ROLE_ADMIN))
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        iResponseService.addResponse(response,idClaim);
        return new ResponseEntity<>("response add successefully", HttpStatus.OK);
    }





















}
