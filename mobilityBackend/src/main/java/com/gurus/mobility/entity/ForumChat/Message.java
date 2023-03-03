package com.gurus.mobility.entity.ForumChat;

import com.gurus.mobility.entity.ForumChat.Enums.MessageStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_msg", nullable = false)
    private Long idMsg;

    @Column(name = "content_msg")
    private String contentMsg;

    @Column(name = "send_date_msg")
    @CreatedDate
    private LocalDateTime sendDateMsg;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_msg")
    private MessageStatus statusMsg;

    @ManyToOne
    @JoinColumn(name = "chat_room_id_ctr")
    private ChatRoom chatRoom;

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
