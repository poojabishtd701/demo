package com.example.allicademo.service;

import com.example.allicademo.domain.Account;

import java.util.List;

/**
 * Service interface for managing {@link Account} entities.
 * Provides methods for creating accounts and retrieving accounts by customer ID.
 */
public interface AccountService {

    /**
     * Retrieves a list of accounts associated with a specific customer.
     *
     * @param customerId the unique identifier of the customer whose accounts are to be retrieved.
     * @return a {@link List} of {@link Account} entities associated with the specified customer ID.
     */
    List<Account> getAccountsByCustomerId(Long customerId);

    /**
     * Creates a new account.
     *
     * @param account the {@link Account} object to be created.
     * @return the created {@link Account} object.
     */
    Account createAccount(Account account);
}
