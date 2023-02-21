package com.gurus.mobility.repository.AccomodationRepository;


import com.gurus.mobility.entity.Accomodation.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayementRepository extends JpaRepository<Payement,Long> {

}
