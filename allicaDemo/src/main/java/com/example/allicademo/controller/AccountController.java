package com.example.allicademo.controller;

import com.example.allicademo.domain.Account;
import com.example.allicademo.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Controller class responsible for handling HTTP requests related to accounts.
 * Provides endpoints to retrieve accounts by customer ID and create a new account.
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountServiceImpl accountService;
    /**
     * Constructor for initializing the AccountController with the provided AccountServiceImpl.
     *
     * @param accountService the account service implementation used to handle business logic for accounts
     */
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieves a list of accounts associated with a specific customer ID.
     *
     * @param customerId the ID of the customer whose accounts are to be retrieved
     * @return a ResponseEntity containing a list of Account objects and an HTTP status of OK (200)
     */
    @GetMapping
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@RequestParam long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }
    /**
     * Creates a new account based on the provided account details.
     *
     * @param account the Account object containing details of the new account to be created
     * @return a ResponseEntity containing the created Account object and an HTTP status of CREATED (201)
     */
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }
}