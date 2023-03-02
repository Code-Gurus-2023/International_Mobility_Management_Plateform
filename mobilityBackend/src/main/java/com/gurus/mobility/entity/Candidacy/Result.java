package com.gurus.mobility.entity.Candidacy;

import com.gurus.mobility.entity.user.StudentSpeciality;
import com.gurus.mobility.entity.user.User;
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

    private float score;


    @Enumerated(EnumType.STRING)
    public StudentSpeciality studentSpeciality;

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

    public float calculateScore() {
        float score;
        switch (studentSpeciality) {
            case COMPUTER_SPECIALTY:
                score = (float) (0.4 * generalAverage + 0.2 * (pl + math + dataMining + springBoot + dotnet) + 0.4 * (english + french + angular) / 3);
                break;
            case TELECOMMUNICATION_SPECIALTY:
                score = (float) (0.3 * generalAverage + 0.4 * (math + pl + dataMining ) + 0.1 * (angular + springBoot) + 0.2 * (dotnet + french + english) / 4);
                break;
            case CIVIL_ENGINEERING:
                score = (float) (0.2 * generalAverage + 0.5 * (french + english + dataMining + springBoot) + 0.3 * (dotnet + math + pl +angular) / 3);
                break;
            case  ELECTRONIC_ENGINEERING:
                score = (float) (0.25 * generalAverage + 0.55 * (french + english + math + pl + dataMining ) + 0.2 * ( dotnet + angular + springBoot) / 3);
                break;

            default:
                score = 0;
        }
        return score;
    }

}