package com.gurus.mobility.entity.Accomodation;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reservation implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long  idRes;
        private Date startDate;
        private Date endDate;
        private String country;
        private int nbPerson;
        private Boolean payementStaus=false;
        @OneToOne
        private Payement payement;



}
