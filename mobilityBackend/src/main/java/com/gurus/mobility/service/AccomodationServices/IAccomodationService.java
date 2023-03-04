package com.gurus.mobility.service.AccomodationServices;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.entity.user.User;
import com.twilio.base.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IAccomodationService {
    public Accomodation saveAccomodation(Accomodation accomodation,Long idOwner);
    public List<Accomodation> getAccomodation();
    public ResponseEntity<Accomodation> getAccomodationById(Long idAcc);
    public ResponseEntity<Accomodation> updateAccomodation(Long idAcc, Accomodation accomodation);
    public ResponseEntity<Accomodation> archiverAnAccommodation(Long idAcc);
    public ResponseEntity<Accomodation> darchiverAnAccommodation(Long idAcc);

    public List<Accomodation> getAllArchiveAccomodation();
    public int nbReservation();
    public int nbLike();
    public List<Accomodation> findAccomodationWithSort(String champ);
    public Page<Accomodation> findAccomodationByPagination(int offset, int pageSize);






    }
