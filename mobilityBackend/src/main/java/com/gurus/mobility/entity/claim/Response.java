package com.gurus.mobility.entity.claim;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idRsp;
    public String descriptionRsp;
    public boolean archiveRsp;
    @Column(name = "created_date")
    @CreatedDate
    public LocalDate creationDateRsp;

    @OneToOne(mappedBy = "response", orphanRemoval = true)
    private Claim claim;

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
}
