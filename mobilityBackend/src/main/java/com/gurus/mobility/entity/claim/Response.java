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
    public int idRsp;
    public String descriptionRsp;
    @Column(name = "created_date")
    @CreatedDate
    public LocalDate creationDateRsp;
}
