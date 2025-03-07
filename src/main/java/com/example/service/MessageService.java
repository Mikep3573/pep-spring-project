package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    // Defaults to constructor injection, Autowired annotation not needed
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return null;
    }

    public List<Message> retrieveMessages() {
        return null;
    }

    public Message retrieveMessage(Integer messageId) {
        return null;
    }

    public Integer deleteMessage(Integer messageId) {
        return null;
    } 

    public Message updateMessage(Integer messageId) {
        return null;
    }

    public List<Message> retrieveUserMessages(Integer accountId) {
        return null;
    }
}
