package com.gurus.mobility.repository.ClaimRepositories;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.entity.claim.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    public List<Claim> findClaimByStateClmIs(State state);
    public List<Claim> findClaimByArchiveClm(boolean arch);
}
