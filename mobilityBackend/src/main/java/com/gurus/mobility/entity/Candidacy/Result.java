package com.gurus.mobility.entity.Candidacy;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    @CreatedDate
    private LocalDate date;

    private float generalAverage;
}