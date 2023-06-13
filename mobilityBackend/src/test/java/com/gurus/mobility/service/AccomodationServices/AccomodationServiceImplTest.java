package com.gurus.mobility.service.AccomodationServices;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.repository.AccomodationRepository.AccomodationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccomodationServiceImplTest {

    @Mock
    private AccomodationRepository accomodationRepository;
    @InjectMocks
    private AccomodationServiceImpl accomodationService;

    @Test
    public void addAcc() {
        Accomodation accomodation = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();

        when(accomodationRepository.save(Mockito.any(Accomodation.class))).thenReturn(accomodation);

        Accomodation savedAccomodation = accomodationService.addAcc(accomodation);

        Assertions.assertThat(savedAccomodation).isNotNull();
    }
    @Test
    public void getAccomodationById()
    {
        Accomodation accomodation = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();
       // accomodationRepository.save(accomodation);
        when(accomodationRepository.findById(1L)).thenReturn(Optional.ofNullable(accomodation));
        ResponseEntity<Accomodation> retrivedAccomodation = accomodationService.getAccomodationById(1L);


        Assertions.assertThat(retrivedAccomodation).isNotNull();
        Assertions.assertThat(retrivedAccomodation.getBody().getIdAcc()).isEqualTo(accomodation.getIdAcc());

    }
    @Test
    public void updateAccomodation()
    {
        Accomodation accomodation = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();
        when(accomodationRepository.findById(1L)).thenReturn(Optional.ofNullable(accomodation));
        when(accomodationRepository.save(Mockito.any(Accomodation.class))).thenReturn(accomodation);

        ResponseEntity<Accomodation> savedAccomodation = accomodationService.updateAccomodation(1L,accomodation);

        Assertions.assertThat(savedAccomodation).isNotNull();
        Assertions.assertThat(savedAccomodation.getBody().getIdAcc()).isEqualTo(accomodation.getIdAcc());
    }

    @Test
    public void getAllAccommodationPage()
    {
        Page<Accomodation> accomodationPage = Mockito.mock(Page.class);

        when(accomodationRepository.findAll(Mockito.any(Pageable.class))).thenReturn(accomodationPage);

          Page<Accomodation> savedAccomodation = accomodationService.findProduitByPagination(1,10);

          Assertions.assertThat(savedAccomodation).isNotNull();
    }

/***              This test method is used to test if an accomodation was deleted
    @Test
    public void deleteAccomodation()
    {
        Accomodation accomodation = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();
        when(accomodationRepository.findById(1L)).thenReturn(Optional.ofNullable(accomodation));

        assertAll(()-> accomodationService.deleteAccomodation(1));
    }
    *****/










        @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}