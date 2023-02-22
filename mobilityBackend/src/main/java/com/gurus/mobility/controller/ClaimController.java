package com.gurus.mobility.controller;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.service.ClaimServices.IClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Claim")
public class ClaimController {
    @Autowired
    private IClaimService iClaimService;

    @PostMapping("/save")
    public void ajouterAlert (@RequestBody Claim claim){
        //iClaimService.createClaim(claim);
    }
}
