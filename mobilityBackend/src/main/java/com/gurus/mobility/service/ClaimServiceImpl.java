package com.gurus.mobility.service;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements IClaimService{
    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public void createClaim(Claim claim) {
        //claim.set
        claimRepository.save(claim);
    }
}
