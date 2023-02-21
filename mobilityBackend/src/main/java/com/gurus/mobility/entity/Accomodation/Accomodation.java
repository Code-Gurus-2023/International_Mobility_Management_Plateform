package com.gurus.mobility.entity.Accomodation;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Accomodation implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long  idAcc;
        private String title;
        private String description;
        private int size;
        private int roomNumber;
        private int bathroomNumber;
        private String image;
        private Boolean furniture;
        private String country;
        private String city;
        private String location;
        private Boolean availability;
        private double price;
        private double bail;
        private String rules;
        private Boolean likes;


}
