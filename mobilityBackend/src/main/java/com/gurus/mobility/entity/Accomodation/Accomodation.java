package com.gurus.mobility.entity.Accomodation;


import com.gurus.mobility.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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


        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name = "Accomodations_Image",
                       joinColumns = {
                        @JoinColumn(name="Accomodation_id")
                       },
                inverseJoinColumns = {
                @JoinColumn(name = "image_id")
                }
        )
        private Set<Image> images;

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @ToString.Exclude
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id", unique = true)
        private User user;

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @ToString.Exclude
        @ManyToMany
        @JoinTable(name = "accomodation_reservations",
                joinColumns = @JoinColumn(name = "accomodation_id_acc"),
                inverseJoinColumns = @JoinColumn(name = "reservations_id_res"))
        private Set<Reservation> reservations = new LinkedHashSet<>();

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
