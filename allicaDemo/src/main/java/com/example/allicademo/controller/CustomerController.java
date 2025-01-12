package com.example.allicademo.controller;

import com.example.allicademo.domain.Customer;
import com.example.allicademo.service.impl.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Controller class responsible for handling HTTP requests related to customers.
 * Provides endpoints to create, retrieve, update, and delete customers.
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerServiceImpl customerService;
    /**
     * Constructor for initializing the CustomerController with the provided CustomerServiceImpl.
     *
     * @param customerService the customer service implementation used to handle business logic for customers
     */
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }
    /**
     * Creates a new customer based on the provided customer details.
     *
     * @param customer the Customer object containing details of the new customer to be created
     * @return a ResponseEntity containing the created Customer object and an HTTP status of CREATED (201)
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(201).body(createdCustomer);
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param id the ID of the customer to be retrieved
     * @return a ResponseEntity containing the Customer object and an HTTP status of OK (200),
     *         or a NOT FOUND (404) status if the customer is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /**
     * Retrieves a list of all customers.
     *
     * @return a ResponseEntity containing a list of all Customer objects and an HTTP status of OK (200)
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    /**
     * Deletes a customer based on their unique ID.
     *
     * @param id the ID of the customer to be deleted
     * @return a ResponseEntity with a NO CONTENT (204) status if the customer is successfully deleted,
     *         or a NOT FOUND (404) status if the customer does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    /**
     * Updates an existing customer with the provided customer details.
     *
     * @param id the ID of the customer to be updated
     * @param customerDetails the Customer object containing the updated details for the customer
     * @return a ResponseEntity containing the updated Customer object and an HTTP status of OK (200)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

}