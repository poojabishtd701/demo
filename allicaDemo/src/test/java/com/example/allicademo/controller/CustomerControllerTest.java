package com.example.allicademo.controller;

import com.example.allicademo.domain.Customer;
import com.example.allicademo.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testCreateCustomer() throws Exception {
        // Create a mock customer object
        Customer customer = new Customer();
        customer.setName("John Doe");

        Customer createdCustomer = new Customer();
        createdCustomer.setId(1L);
        createdCustomer.setName("John Doe");

        // Mock the service layer call
        when(customerService.createCustomer(any(Customer.class))).thenReturn(createdCustomer);

        // Perform POST request to create a customer
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated()) // Assert HTTP Status 201 Created
                .andExpect(jsonPath("$.id").value(1L)) // Assert the created customer ID
                .andExpect(jsonPath("$.name").value("John Doe")); // Assert the customer name

        // Verify if the service method was called
        verify(customerService, times(1)).createCustomer(any(Customer.class));
    }

    @Test
    public void testGetCustomerById_Found() throws Exception {
        // Create a mock customer object
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        // Mock the service layer call
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));

        // Perform GET request to retrieve a customer by ID
        mockMvc.perform(get("/api/v1/customers/{id}", 1L))
                .andExpect(status().isOk()) // Assert HTTP Status 200 OK
                .andExpect(jsonPath("$.id").value(1L)) // Assert customer ID
                .andExpect(jsonPath("$.name").value("John Doe")); // Assert customer name

        // Verify if the service method was called
        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    public void testGetCustomerById_NotFound() throws Exception {
        // Mock the service layer call to return an empty Optional
        when(customerService.getCustomerById(1L)).thenReturn(Optional.empty());

        // Perform GET request to retrieve a customer by ID
        mockMvc.perform(get("/api/v1/customers/{id}", 1L))
                .andExpect(status().isNotFound()); // Assert HTTP Status 404 Not Found

        // Verify if the service method was called
        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        // Create a list of mock customers
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Jane Doe");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        // Mock the service layer call
        when(customerService.getAllCustomers()).thenReturn(customers);

        // Perform GET request to retrieve all customers
        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk()) // Assert HTTP Status 200 OK
                .andExpect(jsonPath("$[0].id").value(1L)) // Assert first customer's ID
                .andExpect(jsonPath("$[0].name").value("John Doe")) // Assert first customer's name
                .andExpect(jsonPath("$[1].id").value(2L)) // Assert second customer's ID
                .andExpect(jsonPath("$[1].name").value("Jane Doe")); // Assert second customer's name

        // Verify if the service method was called
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    public void testDeleteCustomer_Found() throws Exception {
        // Mock the service layer call to return true indicating successful deletion
        when(customerService.deleteCustomer(1L)).thenReturn(true);

        // Perform DELETE request to delete a customer by ID
        mockMvc.perform(delete("/api/v1/customers/{id}", 1L))
                .andExpect(status().isNoContent()); // Assert HTTP Status 204 No Content

        // Verify if the service method was called
        verify(customerService, times(1)).deleteCustomer(1L);
    }

    @Test
    public void testDeleteCustomer_NotFound() throws Exception {
        // Mock the service layer call to return false indicating the customer was not found
        when(customerService.deleteCustomer(1L)).thenReturn(false);

        // Perform DELETE request to delete a customer by ID
        mockMvc.perform(delete("/api/v1/customers/{id}", 1L))
                .andExpect(status().isNotFound()); // Assert HTTP Status 404 Not Found

        // Verify if the service method was called
        verify(customerService, times(1)).deleteCustomer(1L);
    }
}

