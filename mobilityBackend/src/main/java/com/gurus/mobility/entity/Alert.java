package com.gurus.mobility.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idAlrt;
    @NotNull
    public String alrtName;
    public String alrtPhoneNumber;
    @Email
    public String alrtEmail;
    public String regionAlert;
    public String universiteAlrt;
    @Column(name = "created_date")
    @CreatedDate
    public LocalDate alertCreationDate;
    @Column(name = "last_modified_date")
    @LastModifiedDate
    public LocalDate alertModificationDate;
}
