package com.gurus.mobility.repository;

import com.gurus.mobility.entity.claim.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
