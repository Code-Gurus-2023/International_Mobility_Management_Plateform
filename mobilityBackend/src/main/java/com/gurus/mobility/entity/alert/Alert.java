package com.gurus.mobility.entity.alert;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public Long idAlrt;
    @NotNull
    public String alrtName;
    @Enumerated(EnumType.STRING)
    @NotNull
    public Kind alrtKind;
    @Enumerated(EnumType.STRING)
    public Target alrtTarget;
    public String alrtPhoneNumber;
    @Email
    public String alrtEmail;
    public String regionAlert;
    public String coutryAlert;
    public String universiteAlrt;
    @Column(name = "created_date")
    public LocalDateTime alertCreationDate;
    @Column(name = "last_modified_date")
    public LocalDateTime alertModificationDate;
}
