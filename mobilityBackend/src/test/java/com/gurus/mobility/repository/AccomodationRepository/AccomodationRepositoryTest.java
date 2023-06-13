package com.gurus.mobility.repository.AccomodationRepository;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class AccomodationRepositoryTest {

    @Autowired
    private AccomodationRepository accomodationRepository;

    @Test
    @Disabled
   public  void getAllArchiveAccomodation() {

        Accomodation accomodation1 = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Paris")
                .country("France")
                .availability(false)
                .build();

        accomodationRepository.save(accomodation1);

        List<Accomodation> accomodationList=accomodationRepository.getAllArchiveAccomodation();

        Assertions.assertThat(accomodationList).isNotNull();
        Assertions.assertThat(accomodationList).isNotEmpty();
        Assertions.assertThat(accomodationList.get(0).getAvailability()).isEqualTo(false);

    }
    @Test
    public void checkIfAccomodationIsSaved(){
        Accomodation accomodation1 = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Paris")
                .country("France")
                .build();

        Accomodation accomodationSaved = accomodationRepository.save(accomodation1);

        Assertions.assertThat(accomodationSaved).isNotNull();
        Assertions.assertThat(accomodationSaved).isEqualTo(accomodation1);
    }

    @Test
    public void findAccomodationByCity(){
        Accomodation accomodation1 = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Paris")
                .country("France")
                .build();
        Accomodation accomodation2 =
                Accomodation
                        .builder()
                        .idAcc(2L)
                        .title("For rent an accomodation")
                        .city("Paris")
                        .country("France")
                        .build();
        accomodationRepository.save(accomodation1);
        accomodationRepository.save(accomodation2);

        String city="Paris";
        Optional<Accomodation> accomodation= accomodationRepository.findAccomodationByCity(city);
        Assertions.assertThat(accomodation).isNotNull();
        Assertions.assertThat(accomodation).isNotEmpty();
    }

    @Test
    public void updateAccomodation(){
        Accomodation accomodation1 = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Paris")
                .country("France")
                .build();
        Accomodation accomodationSaved = accomodationRepository.save(accomodation1);
        accomodationSaved.setCity("Montréal");
        accomodationSaved.setCountry("Canada");

        Accomodation accomodationUpdated = accomodationRepository.save(accomodationSaved);

        Assertions.assertThat(accomodationUpdated).isNotNull();
        Assertions.assertThat(accomodationUpdated.getCity()).isNotNull();
        Assertions.assertThat(accomodationUpdated.getCountry()).isNotNull();

    }

    @Test
    public void deleteAccomodation(){
        Accomodation accomodation1 = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Paris")
                .country("France")
                .build();
        Accomodation accomodationSaved = accomodationRepository.save(accomodation1);


         accomodationRepository.deleteById(accomodation1.getIdAcc());

         Optional<Accomodation> accomodationList = accomodationRepository.findById(accomodation1.getIdAcc());

         Assertions.assertThat(accomodationList).isEmpty();
    }



















}