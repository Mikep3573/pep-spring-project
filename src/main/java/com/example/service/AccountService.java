package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.ClientSideException;
import com.example.exception.ConflictException;
import com.example.exception.UnauthorizedException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    // Defaults to constructor injection, Autowired annotation not needed
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerUser(Account account) throws ClientSideException, ConflictException {
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

    public Account loginUser(Account account) throws UnauthorizedException {
        // Check if an account exists with that username and password
        // Note: the accountId is given but I figured a more robust implementation 
        // wouldn't assume the front end has the account id 
        // (especially if a user only needs username and password to login)
        Optional<Account> accountOp = accountRepository.findByUsernameAndPassword(account.getUsername(), 
                                                                                    account.getPassword());
        // Throw an error if no account exists
        if (accountOp.isEmpty()) {
            throw new UnauthorizedException("username or password incorrect");
        }

        // Return the retrieved account
        return accountOp.get();
    }
    
}
