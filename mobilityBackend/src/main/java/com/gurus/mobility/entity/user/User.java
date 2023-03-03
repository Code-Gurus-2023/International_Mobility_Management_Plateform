package com.gurus.mobility.entity.user;


import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.entity.Accomodation.Reservation;
import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.entity.ForumChat.ChatRoom;
import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.ForumChat.Notification;
import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.claim.Claim;
import lombok.*;
import org.hibernate.Hibernate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.entity.Accomodation.Reservation;
import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.entity.ForumChat.ChatRoom;
import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.ForumChat.Notification;
import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.claim.Claim;
import lombok.*;
import org.hibernate.Hibernate;


import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "identifiant"),
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(nullable = false, length = 10)
    public String identifiant;
    @Column(nullable = false)

    public String userName;
    @Column(nullable = false)
    @Size(max = 120)
    public String password;
    @Column(nullable = false)
    @Email
    public String email;
    private String profileImage;
    private Boolean verified;
    private String token;
    private String verificationCode;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;
    public String phoneNumber;

    public String kind;
    public String Location;
    @Enumerated(EnumType.STRING)
    public StudentSpeciality studentSpeciality;

    public String studentLevel;
    @Enumerated(EnumType.STRING)
    public ForumStatus forumStatus;

    public String professorDiploma;

    public int experienceYears;
    @ManyToOne
    private FileDB image;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String identifiant, String userName, String email, String password) {
        this.identifiant = identifiant;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Alert> alerts = new LinkedHashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Claim> claims = new LinkedHashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "reservation_id_res", unique = true)
    private Reservation reservation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Accomodation> accomodations;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Candidacy> candidacies = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Result result;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Discussion> discussions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<ChatRoom> chatRooms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Notification> notifications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Offer> offers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Commentaire> commentaires = new LinkedHashSet<>();

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(Set<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(Set<Discussion> discussions) {
        this.discussions = discussions;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Set<Candidacy> getCandidacies() {
        return candidacies;
    }

    public void setCandidacies(Set<Candidacy> candidacies) {
        this.candidacies = candidacies;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(Set<Alert> alerts) {
        this.alerts = alerts;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Set<Accomodation> getAccomodations() {
        return accomodations;
    }

    public void setAccomodations(Set<Accomodation> accomodations) {
        this.accomodations = accomodations;
    }


    public User(String username, String email, String encode) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
