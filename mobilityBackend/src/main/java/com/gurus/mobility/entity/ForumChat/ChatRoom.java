package com.gurus.mobility.entity.ForumChat;

import com.gurus.mobility.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ctr", nullable = false)
    private Long id;

    @Column(name = "chat_id")
    private String chatID;

    @Column(name = "sender_id_ctr")
    private Long senderIdCtr;

    @Column(name = "recipient_id_ctr")
    private Long recipientIdCtr;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Message> messages;


}
