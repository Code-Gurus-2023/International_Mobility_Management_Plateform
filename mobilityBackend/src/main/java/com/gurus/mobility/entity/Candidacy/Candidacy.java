package com.gurus.mobility.entity.Candidacy;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Candidacy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCandidacy;
    private String cv;
    private String coverLetter;
    private String certificate;
    private String firstName;
    private String lastName;
    @Email
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateCandidacy;

    @Pattern(regexp="\\+?[0-9]+")
    private int telephoneNumber;

    private String address;

    @Pattern(regexp = "\\d{4}", message = "Code postal est invalide")
    private int postalCode;

    @Enumerated(EnumType.STRING)
    private DomainCandidacy domainCandidacy;

    @Enumerated(EnumType.STRING)
    private StatusCandidacy statusCandidacy;

    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;

    private boolean isSelected;
    private boolean isArchived;




}
