package com.gurus.mobility.entity.ForumChat;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ctr", nullable = false)
    private Long idCtr;

    @Column(name = "sender_id_ctr")
    private Long senderIdCtr;

    @Column(name = "recipient_id_ctr")
    private Long recipientIdCtr;


}
