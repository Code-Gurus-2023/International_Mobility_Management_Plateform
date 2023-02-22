package com.gurus.mobility.entity.Offer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOffre;

    private String title;
    private String image;
    @CreatedDate
    private LocalDate dateOffre;

    private Integer nbreCandidats;
    @Enumerated(EnumType.STRING)
    private Profil profil;
    @Enumerated(EnumType.STRING)
    private Destination destination;

    @Enumerated(EnumType.STRING)
    private Duration duration;
    private String conditions;
    @Enumerated(EnumType.STRING)
    private Advantages advantages;

}
