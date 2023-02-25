package com.gurus.mobility.service.ClaimServices;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.entity.claim.State;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.UpdateClaimException;
import com.gurus.mobility.repository.ClaimRepositories.ClaimRepository;
import com.gurus.mobility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.gurus.mobility.entity.claim.State.NOT_TRAITED;

@Service
public class ClaimServiceImpl implements IClaimService {
    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createClaim(Claim claim, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UpdateClaimException("object not found with id =" + userId));
        claim.setArchiveClm(false);
        claim.setStateClm(NOT_TRAITED);
        user.getClaims().add(claim);
        claimRepository.save(claim);
        userRepository.save(user);
    }

    @Override
    public void updateClaim(Claim claim, Long id) {
        Claim cl = claimRepository.findById(id).orElseThrow(() -> new UpdateClaimException("object not found with id =" + id));
        if (cl.getStateClm() == NOT_TRAITED)
            claimRepository.save(cl);
    }

    @Override
    public void archiveClaim(Long claimId) {
        Claim cl = claimRepository.findById(claimId).orElseThrow(() -> new UpdateClaimException("object not found with id =" + claimId));
        cl.setArchiveClm(true);
        claimRepository.save(cl);
    }

    @Override
    public List<Claim> getUserClaims(int idUser) {
        List<Claim> Lclaims = null;
        for (Claim clms : userRepository.findById(idUser).get().getClaims()) {
            if (clms.isArchiveClm() == false)
                Lclaims.add(clms);
        }
        return Lclaims;
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public List<Claim> getClaimByStatus(State st) {
        return claimRepository.findClaimByStateClmIs(st);
    }

    @Override
    public List<Claim> getBefore() {
        List<Claim> Lclaims = null;
        for (Claim clms : claimRepository.findAll()) {
            if (clms.getCreationDateClm().isBefore(LocalDate.now()))
                Lclaims.add(clms);
        }
        return Lclaims;
    }

    @Override
    public List<Claim> getAfter() {
        List<Claim> Lclaims = null;
        for (Claim clms : claimRepository.findAll()) {
            if (clms.getCreationDateClm().isAfter(LocalDate.now()))
                Lclaims.add(clms);
        }
        return Lclaims;
    }

    @Override
    public List<Claim> getAtDate() {
        List<Claim> Lclaims = null;
        for (Claim clms : claimRepository.findAll()) {
            if (clms.getCreationDateClm().isEqual(LocalDate.now()))
                Lclaims.add(clms);
        }
        return Lclaims;
    }
    @Override
    public void blockClaim(Long clmId) {
        Claim clm = claimRepository.findById(clmId).orElseThrow(() -> new UpdateClaimException("object not found with id =" + clmId));
        clm.setStateClm(State.BLOCKED);
        claimRepository.save(clm);
    }
    @Override
    public void claimInProgress(Long clmId) {
        Claim clm = claimRepository.findById(clmId).orElseThrow(() -> new UpdateClaimException("object not found with id =" + clmId));
        clm.setStateClm(State.IN_PROGRESS);
        claimRepository.save(clm);
    }
    @Override
    public List<User> sortUsersByClaimsNumber() {

        List<User> users = userRepository.findAll();
        List<User> sortedUsers = null;

        while (users.stream().count() > 0) {
            User user = null;
            long max = 0;
            for (User us : users) {
                if (us.getClaims().stream().count() > max) {
                    max = us.getClaims().stream().count();
                    user = us;
                }
            }
            sortedUsers.add(user);
            users.remove(user);
        }
        return sortedUsers;
    }

    @Override
    public List<Claim> getActiveClaims(){
        return claimRepository.findClaimByArchiveClm(true);
    }
}
