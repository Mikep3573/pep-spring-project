package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.ClientSideException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    // Defaults to constructor injection, Autowired annotation not needed
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) throws ClientSideException{
        // Check that the message text is not blank
        if (message.getMessageText().isEmpty()) {
            throw new ClientSideException("message must have text");
        }

        // Check that the message text is not over 255 characters
        if (message.getMessageText().length() > 255) {
            throw new ClientSideException("message must be at most 255 characters long");
        }

        // Check that postedBy refers to a real, existing user
        Integer postedBy = message.getPostedBy();
        if (accountRepository.findById(postedBy).isEmpty()) {
            throw new ClientSideException("message must refer to an existing user");
        }

        // Persist the new message
        messageRepository.save(message);

        // Return the persisted message
        return messageRepository.findById(message.getMessageId()).get();
    }

    public List<Message> retrieveMessages() {
        // Get all messages from the database
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    public Message retrieveMessage(Integer messageId) {
        // Get message from the database using it's id
        Optional<Message> messageOp = messageRepository.findById(messageId);
        return messageOp.orElse(null);  // Returns either null or a Message object
    }

    public Integer deleteMessage(Integer messageId) {
        // Delete message from the database and return the number of rows affected (0 or 1)
        Integer rowsAffected = messageRepository.deleteByIdAndReturnRowsAffected(messageId);
        return rowsAffected > 0 ? rowsAffected : null;
    } 

    public Message updateMessage(Integer messageId) {
        return null;
    }

    public List<Message> retrieveUserMessages(Integer accountId) {
        return null;
    }
}
