package com.example.allicademo.service;

import com.example.allicademo.domain.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link Customer} entities.
 * Provides methods for creating, retrieving, deleting, and listing customers.
 */
public interface CustomerService {

    /**
     * Creates a new customer.
     *
     * @param customer the {@link Customer} object to be created.
     * @return the created {@link Customer} object.
     */
    Customer createCustomer(Customer customer);

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer.
     * @return an {@link Optional} containing the {@link Customer} if found, or an empty {@link Optional} if not found.
     */
    Optional<Customer> getCustomerById(Long id);

    /**
     * Retrieves all customers.
     *
     * @return a {@link List} of all {@link Customer} entities.
     */
    List<Customer> getAllCustomers();

    /**
     * Deletes a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer to be deleted.
     * @return {@code true} if the customer was successfully deleted, or {@code false} if the customer was not found.
     */
    boolean deleteCustomer(Long id);

    /**
     * Updates an existing customer with the given details.
     *
     * @param id the unique identifier of the customer to be updated.
     * @param customerDetails the {@link Customer} object containing the updated details.
     * @return the updated {@link Customer} object.
     * @throws RuntimeException if no customer is found with the given {@code id}.
     */
    Customer updateCustomer(Long id, Customer customerDetails);
}

