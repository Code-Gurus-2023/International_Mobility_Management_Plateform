package com.gurus.mobility.service.AccomodationServices;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccomodationService {
    public List<Accomodation> getAllAccomodation();
    public ResponseEntity<Accomodation> getAccomodationById(Long idAcc);
    //public ResponseEntity<Accomodation> updateAccomodation(Long idAcc, Accomodation accomodation);
    public ResponseEntity<Accomodation> archiverAnAccommodation(Long idAcc);
    public ResponseEntity<Accomodation> darchiverAnAccommodation(Long idAcc);
    public List<Accomodation> getAllArchiveAccomodation();
    public int nbReservation();
    public int nbLike();
    public List<Accomodation> findAccomodationWithSort(String champ);
    public List<Accomodation> getAllAccommodationPage(Integer pageNo, Integer pageSize);

    Accomodation addAcc(Accomodation accomodation);
    public ResponseEntity<Accomodation> updateAccomodation(Long idAcc, Accomodation accomodation);
    }
