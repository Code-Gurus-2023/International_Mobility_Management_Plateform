package com.gurus.mobility.controller;

import com.gurus.mobility.entity.ForumChat.Message;
import com.gurus.mobility.entity.ForumChat.Notification;
import com.gurus.mobility.service.ForumChatService.IChatRoomService;
import com.gurus.mobility.service.ForumChatService.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload Message chatMessage) {
        var chatId = chatRoomService
                //.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
                        .getChatId(chatMessage.getChatRoom().getSenderIdCtr(), chatMessage.getChatRoom().getRecipientIdCtr(), true);
        //chatMessage.setChatId(chatId.get());

        Message saved = messageService.saveMessage(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getChatRoom().getRecipientIdCtr().toString(),"/queue/messages",
                new Notification(
//                        saved.getId(),
//                        saved.getSenderId(),
//                        saved.getSenderName()));
                          saved.getIdMsg(),
                          saved.getChatRoom().getSenderIdCtr(),
                          saved.getChatRoom().getUser().getUserName()));
    }
}
