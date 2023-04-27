package com.gurus.mobility.repository.ClaimRepositories;

import com.gurus.mobility.entity.claim.Claim;
import com.gurus.mobility.entity.claim.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    public List<Claim> findClaimByStateClmIs(State state);
    public List<Claim> findClaimByArchiveClm(boolean arch);

    //@Query("SELECT (c) FROM Claim c WHERE c.creationDateClm > :date AND c.user_id= :userid")
    //int NBClaimsLastDate(@Param("date") Date date, @Param("userid") Long userid);
}
