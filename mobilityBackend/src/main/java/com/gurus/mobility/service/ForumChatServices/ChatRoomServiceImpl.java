package com.gurus.mobility.service.ForumChatServices;

import com.gurus.mobility.repository.ForumChatRepos.IChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomServiceImpl implements IChatRoomService {

    @Autowired
    IChatRoomRepository chatRoomRepository;
}
