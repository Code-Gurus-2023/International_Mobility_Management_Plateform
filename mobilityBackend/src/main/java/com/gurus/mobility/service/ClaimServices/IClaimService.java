package com.gurus.mobility.service.ClaimServices;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.claim.Claim;

import com.gurus.mobility.entity.claim.State;
import com.gurus.mobility.entity.user.User;

import java.time.LocalDate;
import java.util.List;

public interface IClaimService {

    public void createClaim(Claim claim, Long idUser);
    public void updateClaim(Claim claim,Long id);
    public void archiveClaim(Long id);
    public List<Claim> getAllClaims();
    public List<Claim> getClaimByStatus(State st);
    public List<Claim> getBefore();
    public List<Claim> getAfter();
    public List<Claim> getAtDate();
    public void blockClaim(Long clmId);
    public void claimInProgress(Long clmId);
    public List<User> sortUsersByClaimsNumber();
    public List<Claim> getActiveClaims();
    public List<Claim> getUserClaims(Long idUser);
    public boolean getUseridByClaimid(User user, long id);


}
