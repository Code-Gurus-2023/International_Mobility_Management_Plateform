package com.gurus.mobility.entity.Accomodation;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gurus.mobility.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Accomodation")
public class Accomodation implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long  idAcc;
        private String title;
        private String description;
        private int size;
        private int roomNumber;
        private int bathroomNumber;
        private Boolean furniture;
        private String country;
        private String city;
        private String location;
        private Boolean availability;
        private double price;
        private double bail;
        private String rules;
        private Boolean likes;



        @JsonIgnore
        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @ToString.Exclude
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id", unique = true)
        private User user;
        @JsonIgnore
        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @ToString.Exclude
        @ManyToMany
        @JoinTable(name = "accomodation_reservations",
                joinColumns = @JoinColumn(name = "accomodation_id_acc"),
                inverseJoinColumns = @JoinColumn(name = "reservations_id_res"))
        private Set<Reservation> reservations = new LinkedHashSet<>();
        @JsonIgnore
        public Set<Reservation> getReservations() {
                return reservations;
        }

        public void setReservations(Set<Reservation> reservations) {
                this.reservations = reservations;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }
}
