package com.gurus.mobility.service.ClaimServices;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.repository.ClaimRepositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements IClaimService {
    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public void createClaim(Claim claim) {
        //claim.set
        claimRepository.save(claim);
    }

    @Override
    public void updateClaim(Claim claim, Long id) {

    }

    @Override
    public void archiveClain(Long id) {

    }

    @Override
    public void getUserClaims(Long idUser) {

    }

    @Override
    public void getAllClaims() {

    }
}
