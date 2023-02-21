package com.gurus.mobility.repository.AccomodationRepository;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation,Long> {


}
