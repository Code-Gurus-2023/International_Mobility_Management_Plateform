package com.gurus.mobility.service.ClaimServices;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.entity.user.User;

import java.util.List;

public interface IClaimService {

    public void createClaim(Claim claim, int idUser);
    public void updateClaim(Claim claim,Long id);
    public void archiveClaim(Long id);

    public List<Claim> getUserClaims(int idUser);

    public List<Claim> getAllClaims();






}
