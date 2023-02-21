package com.gurus.mobility.repository.AccomodationRepository;


import com.gurus.mobility.entity.Accomodation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {


}
