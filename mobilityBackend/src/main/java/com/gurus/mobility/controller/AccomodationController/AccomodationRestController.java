package com.gurus.mobility.controller.AccomodationController;


import com.gurus.mobility.entity.Accomodation.APIResponse;
import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.entity.Accomodation.Image;
import com.gurus.mobility.service.AccomodationServices.IAccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/accomodation")
@RestController
public class AccomodationRestController {

        @Autowired
        private IAccomodationService accomodationService;

        @GetMapping("/getAllAccomodation")
        public List<Accomodation> getAccomodation() {
                return accomodationService.getAllAccomodation();
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
         * The purpose of this method is to sort accomodations by a specific field
         ***/
        @GetMapping("/{champ}")
        public APIResponse<List<Accomodation>> findAccomodationWithSort(@PathVariable String champ){
                List<Accomodation> allAccomodations=accomodationService.findAccomodationWithSort(champ);
                return new APIResponse<>(allAccomodations.size(), allAccomodations);
        }
        @GetMapping("/nbLikes")
        public int nbLike() {
                return accomodationService.nbLike();
        }
        @GetMapping("/pagination")
        public ResponseEntity<List<Accomodation>> getAllEmployees(
                @RequestParam(defaultValue = "0") Integer pageNo,
                @RequestParam(defaultValue = "10") Integer pageSize
                )
        {
                List<Accomodation> list = accomodationService.getAllAccommodationPage(pageNo, pageSize);

                return new ResponseEntity<List<Accomodation>>(list, new HttpHeaders(), HttpStatus.OK);
        }

        @PostMapping(value = {"/addAcc"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        public Accomodation addAcc(@RequestPart("accomodation") Accomodation accomodation,
                                   @RequestPart("imageFile") MultipartFile[] file
                                   ){
                try {
                     Set<Image> imageSet= uploadImage(file);
                     accomodation.setImages(imageSet);
                   return   accomodationService.addAcc(accomodation);
                }catch (Exception e){
                        System.out.println(e.getMessage());
                        return null;
                }
        }
        /**
         *To process images and save them to the database
         **/
        public   Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException {
                Set<Image> imageSet=new HashSet<>();
                for(MultipartFile file:multipartFiles){
                        Image image=new Image(
                                file.getOriginalFilename(),
                                file.getContentType(),
                                file.getBytes()
                        );
                        imageSet.add(image);
                }
                return imageSet;
        }

        }