package com.gurus.mobility.entity.Candidacy;

import com.gurus.mobility.entity.user.User;
import lombok.*;

import javax.persistence.*;
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

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}