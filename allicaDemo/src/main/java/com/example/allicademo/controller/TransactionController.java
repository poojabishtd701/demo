package com.example.allicademo.controller;


import com.example.allicademo.domain.Transaction;
import com.example.allicademo.service.impl.TransactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to transactions.
 * Provides endpoints to create transactions and retrieve transactions by account number.
 */
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    /**
     * Constructor for initializing the TransactionController with the provided TransactionServiceImpl.
     *
     * @param transactionService the transaction service implementation used to handle business logic for transactions
     */
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }
    /**
     * Creates a new transaction based on the provided transaction details.
     *
     * @param transaction the Transaction object containing details of the new transaction to be created
     * @return a ResponseEntity containing the created Transaction object and an HTTP status of CREATED (201)
     */
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of transactions associated with a specific account number.
     *
     * @param accountNumber the account number whose transactions are to be retrieved
     * @return a ResponseEntity containing a list of Transaction objects and an HTTP status of OK (200)
     */
    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(@RequestParam String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}
