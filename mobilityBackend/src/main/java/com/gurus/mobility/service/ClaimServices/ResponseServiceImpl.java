package com.gurus.mobility.service.ClaimServices;


import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.entity.claim.Response;
import com.gurus.mobility.entity.claim.State;
import com.gurus.mobility.exception.UpdateClaimException;
import com.gurus.mobility.repository.ClaimRepositories.ClaimRepository;
import com.gurus.mobility.repository.ClaimRepositories.ResponseRepository;
import com.gurus.mobility.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponseServiceImpl implements IResponseService{

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addResponse(Response rsp, Long clmId){
        Claim clm= claimRepository.findById(clmId).orElseThrow(()->new UpdateClaimException("object not found with id ="+clmId));
        clm.setStateClm(State.RESOLVED);
        rsp.setClaim(clm);
        responseRepository.save(rsp);
    }

    public Response getResponseByClaim(Long clmId){
        Claim clm= claimRepository.findById(clmId).orElseThrow(()->new UpdateClaimException("object not found with id ="+clmId));
        return clm.getResponse();
    }

    public void archiveResponse(Long idrsp){
        Response rsp= responseRepository.findById(idrsp).orElseThrow(()->new UpdateClaimException("object not found with id ="+idrsp));
        rsp.setArchiveRsp(true);
        responseRepository.save(rsp);
    }

}
