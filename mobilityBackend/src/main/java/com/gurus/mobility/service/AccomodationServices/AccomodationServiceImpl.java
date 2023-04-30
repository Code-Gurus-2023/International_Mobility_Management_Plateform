package com.gurus.mobility.service.AccomodationServices;


import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.repository.AccomodationRepository.AccomodationRepository;
import com.gurus.mobility.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccomodationServiceImpl implements IAccomodationService{
    @Autowired(required = false)
    private UserRepository userRepository;
    @Autowired(required = false)
    private AccomodationRepository accomodationRepository;
    @Autowired(required = false)
    private ServletContext context;
    /**
     * This method will get all accomodations list
     **/
    @Override
    public List<Accomodation> getAllAccomodation() {
        return accomodationRepository.findAll();
    }

    @Override
    public ResponseEntity<Accomodation> getAccomodationById(Long idAcc) {
        Accomodation accomodation=accomodationRepository.findById(idAcc)
                    .orElseThrow();
                                        return ResponseEntity.ok(accomodation);
    }

    @Transactional
    @Override
    public ResponseEntity<Accomodation> updateAccomodation(Long idAcc, Accomodation accomodation) {
        Accomodation newAccomodation=accomodationRepository.findById(idAcc)
                .orElseThrow();
        newAccomodation.setTitle(accomodation.getTitle());
        newAccomodation.setUser(accomodation.getUser());
        newAccomodation.setDescription(accomodation.getDescription());
        newAccomodation.setPrice(accomodation.getPrice());
        newAccomodation.setBail(accomodation.getBail());
        newAccomodation.setFurniture(accomodation.getFurniture());
        newAccomodation.setSize(accomodation.getSize());
        newAccomodation.setRoomNumber(accomodation.getRoomNumber());
        newAccomodation.setBathroomNumber(accomodation.getBathroomNumber());
        newAccomodation.setAvailability(accomodation.getAvailability());
        newAccomodation.setRules(accomodation.getRules());
        newAccomodation.setCountry(accomodation.getCountry());
        newAccomodation.setCity(accomodation.getCity());
        newAccomodation.setLikes(accomodation.getLikes());
        newAccomodation.setLocation(accomodation.getLocation());
        newAccomodation.setReservations(accomodation.getReservations());
        Accomodation acc=accomodationRepository.save(newAccomodation);
        return ResponseEntity.ok(acc);

    }

    /***The purpose of this methode is to indicate that an accomodation is not available that means it will be archived***/
    @Override
    public ResponseEntity<Accomodation> archiverAnAccommodation(Long idAcc) {
        Accomodation accomodation=accomodationRepository.findById(idAcc)
                .orElseThrow();
            accomodation.setAvailability(false);
            return  ResponseEntity.ok(accomodation);
    }
    /***The purpose of this methode is to indicate that an accomodation is  available that means it will be darchived***/
    @Override
    public ResponseEntity<Accomodation> darchiverAnAccommodation(Long idAcc) {
        Accomodation accomodation=accomodationRepository.findById(idAcc)
                .orElseThrow();
        accomodation.setAvailability(true);
        return  ResponseEntity.ok(accomodation);
    }
    @Override
    public List<Accomodation> getAllArchiveAccomodation() {
        return accomodationRepository.getAllArchiveAccomodation();
    }

    @Override
    public int nbReservation() {
        return 0;
    }

    @Override
    public int nbLike() {
        return accomodationRepository.nblikes();
    }

    /**
     *Sort of accomodation by a specific field
     **/
    public List<Accomodation> findAccomodationWithSort(String champ){
        return accomodationRepository.findAll(Sort.by(Sort.Direction.ASC,champ));
    }

    /**
     * To display accomodations list by page
     **/
    public List<Accomodation> getAllAccommodationPage(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Accomodation> pagedResult = accomodationRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Accomodation>();
        }
    }

    @Override
    public Accomodation addAcc(Accomodation accomodation) {
        return accomodationRepository.save(accomodation);
    }


}

