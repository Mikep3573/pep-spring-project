package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.ClientSideException;
import com.example.exception.ConflictException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    // Defaults to constructor injection, Autowired annotation not needed
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerUser(Account account) throws ClientSideException {
        // Check if the username is blank
        if (account.getUsername().isEmpty()) {
            throw new ClientSideException("username was blank, please provide a username");
        }
        // Check for password length (>= 4)
        if (account.getPassword().length() < 4) {
            throw new ClientSideException("password must be of length at least 4");
        }
        // Check account doesn't already exist with username
        Optional<Account> accountOp = accountRepository.findByUsername(account.getUsername());
        if (accountOp.isPresent()) { 
            throw new ConflictException("account with username " + account.getUsername() + " already exists"); 
        }

        // Persist the account and return the persisted account (id included)
        accountRepository.save(account);
        return accountRepository.findByUsername(account.getUsername()).get();

    }

    public Account loginUser(Account account) {
        return null;
    }
    
}
