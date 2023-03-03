package com.gurus.mobility.service.ClaimServices;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.claim.Claim;

public interface IClaimService {

    public void createClaim(Claim claim);
    public void updateClaim(Claim claim,Long id );
    public void archiveClain(Long id);
    public void getUserClaims(Long idUser);
    public void getAllClaims();






}
