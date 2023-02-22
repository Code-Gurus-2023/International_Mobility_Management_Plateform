package com.gurus.mobility.service.ClaimServices;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.UpdateClaimException;
import com.gurus.mobility.repository.ClaimRepositories.ClaimRepository;
import com.gurus.mobility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        claim.setArchiveClm(false);
        claim.setStateClm(NOT_TRAITED);
        User user = userRepository.findById(userId).orElseThrow(()->new UpdateClaimException("object not found with id ="+userId));
        user.getClaims().add(claim);
        claimRepository.save(claim);
        userRepository.save(user);
    }

    @Override
    public void updateClaim(Claim claim, Long id) {
            Claim cl= claimRepository.findById(id).orElseThrow(()->new UpdateClaimException("object not found with id ="+id));
            if(cl.getStateClm()==NOT_TRAITED)
                claimRepository.save(cl);
    }

    @Override
    public void archiveClaim(Long claimId) {
        Claim cl= claimRepository.findById(claimId).orElseThrow(()->new UpdateClaimException("object not found with id ="+claimId));
        cl.setArchiveClm(true);
        claimRepository.save(cl);
    }

    @Override
    public List<Claim> getUserClaims(int idUser) {
        List<Claim> Lclaims=null;
        for (Claim clms:userRepository.findById(idUser).get().getClaims()) {
            if(clms.isArchiveClm()==false)
                Lclaims.add(clms);
        }
        return Lclaims;
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
