package com.gurus.mobility.repository.ForumChatRepos;

import com.gurus.mobility.entity.ForumChat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySenderIdCtrAndRecipientIdCtr(Long senderId, Long recipientId);
}
