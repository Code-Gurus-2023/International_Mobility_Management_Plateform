package com.gurus.mobility.entity.ForumChat;

import com.gurus.mobility.entity.ForumChat.Enums.MessageStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_msg", nullable = false)
    private Long idMsg;

    @Column(name = "content_msg")
    private String contentMsg;

    @Column(name = "send_date_msg")
    private LocalDateTime sendDateMsg;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_msg")
    private MessageStatus statusMsg;

}
