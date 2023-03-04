package com.gurus.mobility.controller.AccomodationController;


import com.gurus.mobility.entity.Accomodation.APIResponse;
import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.service.AccomodationServices.IAccomodationService;
import com.twilio.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/accomodation")
@RestController
public class AccomodationRestController {

        @Autowired
        private IAccomodationService accomodationService;

        @PostMapping("/addAccomodation/{idOwner}")
        public Accomodation saveAccomodation(@RequestBody Accomodation accomodation, @PathVariable("idOwner")Long idOwner) {
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
        public ResponseEntity<Accomodation> archiverAnAccomodation(@PathVariable("idAcc") Long idAcc) {
                return accomodationService.archiverAnAccommodation(idAcc);
        }

        /***The purpose of this methode is to indicate that an accomodation is  available that means it will be darchived***/
        @PutMapping("/darchiveAnAccomodation/{idAcc}")
        public ResponseEntity<Accomodation> darchiverAnAccommodation(@PathVariable("idAcc") Long idAcc) {
                return accomodationService.darchiverAnAccommodation(idAcc);
        }

        /***The purpose of this methode is to get all archived accomodations that's means all accomodations are not available***/
        @GetMapping("/getAllArchiveAccomodation")
        public List<Accomodation> getAllArchiveAccomodation() {
                return accomodationService.getAllArchiveAccomodation();
        }
        /***
         *The purpose of this method is to display accomodations list by page
         ***/
        @GetMapping("/pagination/{offset}/{pageSize}")
        public APIResponse<Page<Accomodation>> getProduitWithPagination(@PathVariable int offset, @PathVariable int pageSize){
                Page<Accomodation> accomodationWithPagination = accomodationService.findAccomodationByPagination(offset, pageSize);
                return new APIResponse<>(accomodationWithPagination.getPageSize(), accomodationWithPagination);
        }
        /***
         * The purpose of this method is to sort accomodations by a specific field
         ***/
        @GetMapping("/{champ}")
        public APIResponse<List<Accomodation>> findAccomodationWithSort(@PathVariable String champ){
                List<Accomodation> allAccomodations=accomodationService.findAccomodationWithSort(champ);
                return new APIResponse<>(allAccomodations.size(), allAccomodations);
        }




}