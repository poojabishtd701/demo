package com.example.allicademo.service;

import com.example.allicademo.domain.Transaction;

import java.util.List;

/**
 * Service interface for managing {@link Transaction} entities.
 * Provides methods for retrieving transactions based on account numbers.
 */
public interface TransactionService {

    /**
     * Retrieves a list of transactions for a specific account.
     *
     * @param accountNumber the account number for which transactions are to be retrieved.
     * @return a {@link List} of {@link Transaction} entities associated with the specified account number.
     */
    List<Transaction> getTransactionsByAccountNumber(String accountNumber);
    /**
     * Creates a new transaction.
     *
     * @param transaction the {@link Transaction} object to be created.
     * @return the created {@link Transaction} object.
     */
    Transaction createTransaction(Transaction Transaction);
}
