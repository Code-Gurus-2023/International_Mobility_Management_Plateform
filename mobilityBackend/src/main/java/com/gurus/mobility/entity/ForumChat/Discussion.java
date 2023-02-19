package com.gurus.mobility.entity.ForumChat;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discussion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dsc", nullable = false)
    private Long idDsc;

    @Column(name = "name_dsc", length = 50)
    private String nameDsc;

    @Column(name = "nbr_message_dsc")
    private Integer nbrMessageDsc;

    @Column(name = "views_dsc")
    private Long viewsDsc;

    @Column(name = "last_message_date")
    private LocalDateTime lastMessageDate;

    @Column(name = "is_active")
    private Boolean isActive;


}
