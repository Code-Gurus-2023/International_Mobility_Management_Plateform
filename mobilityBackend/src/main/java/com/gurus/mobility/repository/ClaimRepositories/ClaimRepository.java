package com.gurus.mobility.repository.ClaimRepositories;

import com.gurus.mobility.entity.claim.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
