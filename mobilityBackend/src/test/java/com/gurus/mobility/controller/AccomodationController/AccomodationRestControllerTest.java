package com.gurus.mobility.controller.AccomodationController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.service.AccomodationServices.AccomodationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(controllers = AccomodationRestController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccomodationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccomodationServiceImpl accomodationService;

    @Autowired
    private ObjectMapper objectMapper;

    private Accomodation accomodation;

    @BeforeEach
    void init() {
        //mockMvc = MockMvcBuilders.standaloneSetup(AccomodationRestController.class).build();
         accomodation = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();

    }
     @Test
    public void addAccomodation() throws Exception {

        given(accomodationService.addAcc(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

         ResultActions response = mockMvc.perform(post("/accomodation/addAccomodation")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(accomodation)));

         response.andExpect(MockMvcResultMatchers.status().isOk());

     }

    @Test
    public void getAllAccomodationWithPagination() throws Exception {

         accomodation = Accomodation
                .builder()
                .idAcc(1L)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();

        List<Accomodation> accomodations = new ArrayList<>();
        accomodations.add(accomodation);

        Pageable pageable = PageRequest.of(0, 10); // Offset: 0, PageSize: 10
        Page<Accomodation> page = new PageImpl<>(accomodations, pageable, accomodations.size());

        // Configurez le comportement du mock du service
        when(accomodationService.findProduitByPagination(anyInt(), anyInt())).thenReturn(page);

        // Effectuez la requête GET avec les paramètres d'offset et de pageSize
        mockMvc.perform(get("/accomodation/pagination/{offset}/{pageSize}", 0, 10))

                .andExpect(status().isOk());
    }

    @Test
    public void getAccomodationById() throws Exception {
        Long idAcc = 1L;
        accomodation = Accomodation
                .builder()
                .idAcc(idAcc)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();

        when(accomodationService.getAccomodationById(1L)).thenReturn(ResponseEntity.ok(accomodation));


        mockMvc.perform(get("/accomodation/getAccomodationById/{idAcc}", idAcc))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idAcc").value(idAcc));
    }
    @Test
    public void testGetAccomodationById_NotFound() throws Exception {

        Long idAcc = 1L;
        accomodation = Accomodation
                .builder()
                .idAcc(idAcc)
                .title("For rent an accomodation")
                .city("Montréal")
                .country("Canada")
                .availability(false)
                .build();
        // Configurez le comportement du mock du service pour renvoyer une réponse "Not Found"
        when(accomodationService.getAccomodationById(idAcc)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(get("/accomodation/getAccomodationById/{idAcc}", idAcc))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAccomodation() throws Exception {
        Long id =1L;
        when(accomodationService.updateAccomodation(id,accomodation)).thenReturn(ResponseEntity.ok(accomodation));

        ResultActions response = mockMvc.perform(put("/apiEtudiant/updateEtudiant/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accomodation)));
    }

}













