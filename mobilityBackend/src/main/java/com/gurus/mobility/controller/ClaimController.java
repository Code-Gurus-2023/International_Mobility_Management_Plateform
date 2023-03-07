package com.gurus.mobility.controller;

import com.gurus.mobility.entity.claim.Claim;

import com.gurus.mobility.entity.claim.Response;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.ClaimRepositories.ClaimRepository;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.ClaimServices.IClaimService;
import com.gurus.mobility.service.ClaimServices.IResponseService;
import com.gurus.mobility.service.User.IUserService;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
@RequestMapping("/api/Claim/")
public class ClaimController {
    public User authorisation(){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
    }

    @Autowired
    private IClaimService iClaimService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IResponseService iResponseService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClaimRepository claimRepository;

    //simple user can create new claim
    @PostMapping("creat")
    public ResponseEntity createClaim(@RequestBody Claim claim){
        User user = authorisation();

        iClaimService.createClaim(claim, user.getId());
        return new ResponseEntity<>("claim sended successefully", HttpStatus.OK);
    }

    //simple user can delete claim that was created before
    @PutMapping("{idClaim}")
    public ResponseEntity deleteClaim(@PathVariable("idClaim")Long idClaim){
        User user = authorisation();
        if(iClaimService.getUseridByClaimid(user,idClaim)){
            iClaimService.archiveClaim(idClaim);
            return new ResponseEntity<>("claim deleted successefully", HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }

    @PutMapping("update/{idClaim}")
    public ResponseEntity updateClaim(@RequestBody Claim claim, @PathVariable("idClaim")Long idClaim){
        User user = authorisation();
        if(iClaimService.getUseridByClaimid(user,idClaim)){
            iClaimService.updateClaim(claim, idClaim);
            return new ResponseEntity<>("claim updated successefully", HttpStatus.OK);
        }
        return new ResponseEntity<>("you don't have access to this url",HttpStatus.FORBIDDEN);
    }

    // admin user can access all claims, even they was archived by simple users
    @GetMapping("allClaims")
    public ResponseEntity<List<Claim>> getAllClaims(){

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        User user = userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
        //if(!user.getRoles().contains(ERole.ROLE_ADMIN))
            //return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iClaimService.getAllClaims(),HttpStatus.OK);
    }

    //simple user can access their active claims
    @GetMapping("myClaims")
    public ResponseEntity<List<Claim>> getClaimsByUser(){
        User user = authorisation();
        return new ResponseEntity<>(iClaimService.getUserClaims(user.getId()),HttpStatus.OK);
    }

    //admin user access all active claims to respond the claims
    @GetMapping("activeClaims")
    public ResponseEntity<List<Claim>> getActiveClaims(@PathVariable("idUser")Long idUser){
        if(!iUserService.findById(idUser).getRoles().contains(ERole.ROLE_ADMIN))
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iClaimService.getActiveClaims(),HttpStatus.OK);
    }

    //admin user can get users sorted by number of claims
    @GetMapping("sortUsers")
    public ResponseEntity<List<User>> getUsersByClaims(){
        User user = authorisation();
        if(user==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(iClaimService.sortUsersByClaimsNumber(),HttpStatus.OK);

    }


    //user admin respond to specific claim
    @PostMapping("responseclaim/{idClaim}")
    public ResponseEntity respondClaim(@RequestBody Response response, @PathVariable("idClaim")Long idClaim){
        User user = authorisation();
        if(user!=null){
            iResponseService.addResponse(response,idClaim);
            return new ResponseEntity<>("response add successefully", HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }

    @PutMapping("/block/{idClaim}")
    public ResponseEntity blockClaim(@PathVariable("idClaim")Long idClaim){
        User user = authorisation();
        if(user==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        iClaimService.blockClaim(idClaim);
        return new ResponseEntity<>("claim blocked successefully", HttpStatus.OK);
    }



}
