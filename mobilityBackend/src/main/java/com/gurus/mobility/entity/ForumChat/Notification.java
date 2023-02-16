package com.gurus.mobility.entity.ForumChat;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ntf", nullable = false)
    private Integer idNtf;

    @Column(name = "sender_id_ntf")
    private Long senderIdNtf;

    @Column(name = "sender_name_ntf", length = 50)
    private String senderNameNtf;

}
