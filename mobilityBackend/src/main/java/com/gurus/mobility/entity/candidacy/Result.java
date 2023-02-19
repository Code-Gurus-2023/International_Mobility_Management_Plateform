package com.gurus.mobility.entity.candidacy;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Result  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResult;
    private float pl;
    private float dataMining;
    private float springBoot;
    private float angular;
    private float dotnet;
    private float english;
    private float french;
    private float math;
    private float generalAverage;
}