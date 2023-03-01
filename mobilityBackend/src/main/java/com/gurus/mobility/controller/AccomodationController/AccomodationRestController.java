package com.gurus.mobility.controller.AccomodationController;


import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.service.AccomodationServices.IAccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/accomodation")
public class AccomodationRestController {

        @Autowired
        private IAccomodationService accomodationService;

        @PostMapping("/addAccomodation/{idOwner}")
        public Accomodation saveAccomodation(@RequestBody Accomodation accomodation, @PathVariable("idOwner") Integer idOwner) {
                return accomodationService.saveAccomodation(accomodation, idOwner);
        }

        @GetMapping("/getAllAccomodation")
        public List<Accomodation> getAccomodation() {
                return accomodationService.getAccomodation();
        }

        @GetMapping("/getAccomodationById/{idAcc}")
        public ResponseEntity<Accomodation> getAccomodationById(@PathVariable("idAcc") Long idAcc) {
                return accomodationService.getAccomodationById(idAcc);
        }

        @PutMapping("/updateAccomodation/{idAcc}")
        public ResponseEntity<Accomodation> updateAccomodation(@PathVariable("idAcc") Long idAcc, @RequestBody Accomodation accomodation) {
                return accomodationService.updateAccomodation(idAcc, accomodation);
        }

        /***The purpose of this methode is to indicate that an accomodation is not available that means it will be archived***/
        @PutMapping("/archiveAnAccomodation/{idAcc}")
        public ResponseEntity<Accomodation> archiverAnAccomodation(@PathVariable Long idAcc) {
                return accomodationService.archiverAnAccommodation(idAcc);
        }

        /***The purpose of this methode is to indicate that an accomodation is  available that means it will be darchived***/
        @PutMapping("/archiveAnAccomodation/{idAcc}")
        public ResponseEntity<Accomodation> darchiverAnAccommodation(@PathVariable Long idAcc) {
                return accomodationService.darchiverAnAccommodation(idAcc);
        }

        /***The purpose of this methode is to get all archived accomodations that's means all accomodations are not available***/
        @GetMapping("/getAllArchiveAccomodation")
        public List<Accomodation> getAllArchiveAccomodation() {
                return accomodationService.getAllArchiveAccomodation();
        }





}