package com.gurus.mobility.entity.ForumChat;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cmt", nullable = false)
    private Long idCmt;

    @Column(name = "up_vote_cmt")
    private Long upVoteCmt;

    @Column(name = "content_cmt")
    private String contentCmt;

    @Column(name = "creation_date_cmt")
    private LocalDate creationDateCmt;


}
