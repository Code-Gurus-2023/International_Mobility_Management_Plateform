package com.gurus.mobility.entity.Candidacy;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.io.Serializable;
import java.time.LocalDate;


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
    @Column(nullable = false, unique = true)
    private String email;

    @CreatedDate
    private LocalDate dateCandidacy;


    private int telephoneNumber;

    private String address;


    private int postalCode;

    @Enumerated(EnumType.STRING)
    private DomainCandidacy domainCandidacy;

    @Enumerated(EnumType.STRING)
    private StatusCandidacy statusCandidacy;

    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;


    private boolean selectionne;

    private boolean archive;

    public boolean getSelectionne() {
        return  this.selectionne;
    }

    public boolean getArchive() {
        return this.archive;
    }
}
