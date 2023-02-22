package com.gurus.mobility.entity.Offer;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFormat;
=======
import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.user.User;
>>>>>>> e01815e2b8578922b45e60f308a06ff1c2133c51
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "offer", orphanRemoval = true)
    private Set<Candidacy> candidacies = new LinkedHashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "offer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Commentaire> commentaires = new LinkedHashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Candidacy> getCandidacies() {
        return candidacies;
    }

    public void setCandidacies(Set<Candidacy> candidacies) {
        this.candidacies = candidacies;
    }
}
