package com.gurus.mobility.repository.AccomodationRepository;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation,Long> {

    @Query("select a from  Accomodation a where a.availability=false")
    public List<Accomodation> getAllArchiveAccomodation();
    @Query("select count(a.likes) from Accomodation a ")
    public int nblikes();

}
