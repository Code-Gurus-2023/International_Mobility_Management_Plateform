package com.gurus.mobility.entity.Candidacy;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.Offer.Profil;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.User;
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
    private String email;

    @CreatedDate
    private LocalDate dateCandidacy;

    private int telephoneNumber;

    private String address;

    private int postalCode;

    private float moyenneGenerale;

    private float scoree;


    @Enumerated(EnumType.STRING)
    private DomainCandidacy domainCandidacy;

    @Enumerated(EnumType.STRING)
    private StatusCandidacy statusCandidacy;

    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;


    public Candidacy(Integer id, String nom_de_candidature, boolean b) {

    }


    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "offer_id_offre")
    private Offer offer;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }






}
