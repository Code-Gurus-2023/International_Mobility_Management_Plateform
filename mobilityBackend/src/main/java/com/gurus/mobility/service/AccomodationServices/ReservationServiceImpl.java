package com.gurus.mobility.service.AccomodationServices;


import com.gurus.mobility.entity.Accomodation.Payement;
import com.gurus.mobility.entity.Accomodation.Reservation;
import com.gurus.mobility.repository.AccomodationRepository.PayementRepository;
import com.gurus.mobility.repository.AccomodationRepository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements IReservationService{

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PayementRepository payementRepository;

    @Override
    public Reservation AffectPayementAndSaveReservation(Reservation reservation,Long idPayement) {
        Reservation reservation1=reservationRepository.save(reservation);
        Payement payement=payementRepository.findById(idPayement)
                .orElseThrow();
                reservation1.setPayement(payement);
               return reservationRepository.save(reservation1);
    }

    @Override
    public List<Reservation> getReservation() {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> getReservationById(Long idRes) {
        Reservation reservation=reservationRepository.findById(idRes)
                .orElseThrow();
        return ResponseEntity.ok(reservation);
    }

    @Override
    public ResponseEntity<Reservation> updateReservation(Long idRes, Reservation reservation) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteReservation(Long idRes) {
        return null;
    }
}
