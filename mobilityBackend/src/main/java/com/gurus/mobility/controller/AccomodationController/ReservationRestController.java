package com.gurus.mobility.controller.AccomodationController;


import com.gurus.mobility.entity.Accomodation.Reservation;
import com.gurus.mobility.service.AccomodationServices.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
    @Autowired
    private IReservationService reservationService;


    @GetMapping("/getReservationById/{idRes}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("idRes")Long idRes){
        return reservationService.getReservationById(idRes);
    }
    @PutMapping("/AffectPayementAndSaveReservation/{idPayement}")
    public Reservation AffectPayementAndSaveReservation(Reservation reservation,Long idPayement){
        return reservationService.AffectPayementAndSaveReservation(reservation,idPayement);
    }







}
