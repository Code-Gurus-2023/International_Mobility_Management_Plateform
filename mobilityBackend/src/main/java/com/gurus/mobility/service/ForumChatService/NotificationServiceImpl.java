package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.repository.ForumChatRepos.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService{

    @Autowired
    INotificationRepository notificationRepository;
}
