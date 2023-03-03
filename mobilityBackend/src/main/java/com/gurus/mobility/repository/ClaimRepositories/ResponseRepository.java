package com.gurus.mobility.repository.ClaimRepositories;

import com.gurus.mobility.entity.claim.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
}
