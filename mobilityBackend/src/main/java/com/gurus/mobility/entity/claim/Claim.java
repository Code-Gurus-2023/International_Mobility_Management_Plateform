package com.gurus.mobility.entity.claim;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Claim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idClm;
    @NotNull
    public String subjectClm;
    @NotNull
    public String descriptionClm;

    public String pathImageClm;
    @Enumerated(EnumType.STRING)
    public Type typeClm;
    @Enumerated(EnumType.STRING)
    public State stateClm;
    public boolean archiveClm;
    @Column(name = "created_date")
    @CreatedDate
    public LocalDate creationDateClm;
    @Column(name = "last_modified_date")
    @LastModifiedDate
    public LocalDate modificationDateClm;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "response_id_rsp", unique = true)
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
