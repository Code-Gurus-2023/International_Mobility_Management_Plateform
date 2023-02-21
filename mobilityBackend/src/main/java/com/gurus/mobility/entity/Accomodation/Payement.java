package com.gurus.mobility.entity.Accomodation;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  idPayement;
    private String name;
    private String currency;
    private double amount;
    private String successUrl;
    private String cancelUrl;
    @OneToOne(mappedBy = "payement", cascade = CascadeType.ALL)
    private Reservation reservation;

}
