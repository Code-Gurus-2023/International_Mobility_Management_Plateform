package com.gurus.mobility.entity.ForumChat;

import com.gurus.mobility.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cmt", nullable = false)
    private Long idCmt;

    @Column(name = "up_vote_cmt")
    private Long upVoteCmt;

    @Column(name = "content_cmt")
    private String contentCmt;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "creation_date_cmt")
    @CreatedDate
    private LocalDate creationDateCmt;


    @ManyToOne
    @JoinColumn(name = "discussion_id_dsc")
    private Discussion discussion;


}
