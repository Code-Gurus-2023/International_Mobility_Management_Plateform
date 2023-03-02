package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.repository.ForumChatRepos.IChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface IChatRoomService {

    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist);


}
