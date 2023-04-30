package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.entity.ForumChat.ChatRoom;
import com.gurus.mobility.repository.ForumChatRepos.IChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements IChatRoomService {

    @Autowired
    IChatRoomRepository chatRoomRepository;

    @Override
    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {
        return chatRoomRepository
                .findBySenderIdCtrAndRecipientIdCtr(senderId, recipientId)
                .map(ChatRoom::getChatID)
                .or(() -> {
                    if(!createIfNotExist)
                        return Optional.empty();

                    var chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatID(chatId)
                            .senderIdCtr(senderId)
                            .recipientIdCtr(recipientId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatID(chatId)
                            .senderIdCtr(recipientId)
                            .recipientIdCtr(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
