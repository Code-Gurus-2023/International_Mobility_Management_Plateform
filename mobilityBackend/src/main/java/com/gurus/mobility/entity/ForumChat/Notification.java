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
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ntf", nullable = false)
    private Integer idNtf;

    @Column(name = "sender_id_ntf")
    private Long senderIdNtf;

    @Column(name = "sender_name_ntf", length = 50)
    private String senderNameNtf;

}
