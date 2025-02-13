package com.skyllx.rental.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyllx.rental.dao.MessageRepository;
import com.skyllx.rental.model.Message;

@Service
public class ChatService {
    @Autowired
    private MessageRepository messageRepository;

    // Send a new message
    public void sendMessage(String sender, String receiver, String text) {
        Message message = new Message();
        message.setSenderUsername(sender);
        message.setReceiverUsername(receiver);
        message.setText(text);
        messageRepository.save(message);
    }

    // Get all messages between two users
    public List<Message> getChatHistory(String user1, String user2) {
        return messageRepository.findAll()
                .stream()
                .filter(msg -> (msg.getSenderUsername().equals(user1) && msg.getReceiverUsername().equals(user2)) ||
                               (msg.getSenderUsername().equals(user2) && msg.getReceiverUsername().equals(user1)))
                .toList();
    }
}
