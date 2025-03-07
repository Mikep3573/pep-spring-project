package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    // Note: Autowired not needed as the default is contructor injection
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register")
    public ResponseEntity<Account> postRegister(@RequestBody Account account) {
        // Attempt to register the account
        Account persistedAcc = accountService.registerUser(account);
        return ResponseEntity.ok()
                            .body(persistedAcc);
    }

    @PostMapping("login")
    public ResponseEntity<Account> postLogin(@RequestBody Account account) {
        // Attempt to login the account
        Account persistedAcc = accountService.loginUser(account);
        return ResponseEntity.ok()
                            .body(persistedAcc);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        // Attempt to persist the message
        Message persistedMessage = messageService.createMessage(message);
        return ResponseEntity.ok()
                            .body(persistedMessage);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getMessages() {
        // Retrieve all messages in the message table
        List<Message> messages = messageService.retrieveMessages();
        return ResponseEntity.ok() 
                            .body(messages);
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        // Attempt to retrieve a message in the database by it's id
        Message message = messageService.retrieveMessage(messageId);
        System.out.println(message);
        return ResponseEntity.ok()
                            .body(message);
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId) {
        // Attempt to delete a message in the database by it's id
        Integer rowsAffected = messageService.deleteMessage(messageId);
        return ResponseEntity.ok()
                            .body(rowsAffected);
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Message> patchMessageById(@PathVariable Integer messageId, @RequestBody String messageText) {
        return null;
    }

    @GetMapping("accounts/{accountId}/messages") 
    public ResponseEntity<List<Message>> getAccountMessages(@PathVariable Integer accountId) {
        return null;
    }



    
}
