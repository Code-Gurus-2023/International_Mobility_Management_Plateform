package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.repository.ForumChatRepos.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    IMessageRepository messageRepository;
}
