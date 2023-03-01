package com.gurus.mobility.service.AccomodationServices;


import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.AccomodationRepository.AccomodationRepository;
import com.gurus.mobility.repository.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Map;

@Service
public class AccomodationServiceImpl implements IAccomodationService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;

    @Override
    public Accomodation saveAccomodation(Accomodation accomodation,Integer idOwner) {
        User user=this.userRepository.findById(idOwner).orElseThrow(null);
        accomodation.setUser(user);
        return accomodationRepository.save(accomodation);
    }

    @Override
    public List<Accomodation> getAccomodation() {
        return accomodationRepository.findAll();
    }

    @Override
    public ResponseEntity<Accomodation> getAccomodationById(Long idAcc) {
        Accomodation accomodation=accomodationRepository.findById(idAcc)
                    .orElseThrow(()->new NotFoundException("We didn't found the accomodation"));
                                        return ResponseEntity.ok(accomodation);
    }

    @Override
    public ResponseEntity<Accomodation> updateAccomodation(Long idAcc, Accomodation accomodation) {
        Accomodation newAccomodation=accomodationRepository.findById(idAcc)
                .orElseThrow(()->new NotFoundException("we didn't found the accomodation to update it"));
        newAccomodation.setTitle(accomodation.getTitle());
        newAccomodation.setUser(accomodation.getUser());
        newAccomodation.setDescription(accomodation.getDescription());
        newAccomodation.setPrice(accomodation.getPrice());
        newAccomodation.setBail(accomodation.getBail());
        newAccomodation.setCity(accomodation.getCity());
        newAccomodation.setFurniture(accomodation.getFurniture());
        newAccomodation.setImage(accomodation.getImage());
        newAccomodation.setSize(accomodation.getSize());
        newAccomodation.setRoomNumber(accomodation.getRoomNumber());
        newAccomodation.setAvailability(accomodation.getAvailability());
        newAccomodation.setRules(accomodation.getRules());
        newAccomodation.setCountry(accomodation.getCountry());
        Accomodation acc=accomodationRepository.save(newAccomodation);
        return ResponseEntity.ok(acc);

    }
    /***The purpose of this methode is to indicate that an accomodation is not available that means it will be archived***/
    @Override
    public ResponseEntity<Accomodation> archiverAnAccommodation(Long idAcc) {
        Accomodation accomodation=accomodationRepository.findById(idAcc)
                .orElseThrow(()->new NotFoundException("We didn't found the accomodation you are looking for"));
            accomodation.setAvailability(false);
            return  ResponseEntity.ok(accomodation);
    }
    /***The purpose of this methode is to indicate that an accomodation is  available that means it will be darchived***/
    @Override
    public ResponseEntity<Accomodation> darchiverAnAccommodation(Long idAcc) {
        Accomodation accomodation=accomodationRepository.findById(idAcc)
                .orElseThrow(()->new NotFoundException("We didn't found the accomodation you are looking for"));
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
        return 0;
    }

}
