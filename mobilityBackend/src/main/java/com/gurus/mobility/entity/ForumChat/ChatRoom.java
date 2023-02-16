package com.gurus.mobility.entity.ForumChat;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ctr", nullable = false)
    private Long idCtr;

    @Column(name = "sender_id_ctr")
    private Long senderIdCtr;

    @Column(name = "recipient_id_ctr")
    private Long recipientIdCtr;


}
