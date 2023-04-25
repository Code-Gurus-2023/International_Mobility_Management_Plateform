package com.gurus.mobility.service.AccomodationServices;


import com.gurus.mobility.entity.Accomodation.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IReservationService {


    public Reservation AffectPayementAndSaveReservation(Reservation reservation,Long idPayement);
    public List<Reservation> getReservation();
    public ResponseEntity<Reservation> getReservationById(Long idRes);
    public ResponseEntity<Reservation> updateReservation(Long idRes, Reservation reservation);
    public ResponseEntity<Map<String,Boolean>> deleteReservation(Long idRes);


}
