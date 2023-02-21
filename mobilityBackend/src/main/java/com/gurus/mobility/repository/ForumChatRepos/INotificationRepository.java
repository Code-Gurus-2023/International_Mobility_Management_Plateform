package com.gurus.mobility.repository.ForumChatRepos;

import com.gurus.mobility.entity.ForumChat.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Integer> {
}
