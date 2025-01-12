package com.example.allicademo.service;

import com.example.allicademo.dao.entity.CustomerEntity;
import com.example.allicademo.dao.entity.CustomerRepository;
import com.example.allicademo.domain.Customer;
import com.example.allicademo.mapper.CustomerMapper;
import com.example.allicademo.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerEntity customerEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup a CustomerEntity object for the tests
        customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("John Doe");

        // Setup a Customer object for the tests
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
    }

    @Test
    void testCreateCustomer() {
        // Given

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);


        // When
        Customer createdCustomer = customerService.createCustomer(customer);

        // Then
        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getName());
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
    }

    @Test
    void testGetCustomerById() {
        // Given
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));


        // When
        Optional<Customer> retrievedCustomer = customerService.getCustomerById(customerId);

        // Then
        assertTrue(retrievedCustomer.isPresent());
        assertEquals("John Doe", retrievedCustomer.get().getName());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void testGetAllCustomers() {
        // Given
        List<CustomerEntity> customerEntities = Collections.singletonList(customerEntity);
        when(customerRepository.findAll()).thenReturn(customerEntities);


        // When
        List<Customer> customers = customerService.getAllCustomers();

        // Then
        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testDeleteCustomer() {
        // Given
        Long customerId = 1L;
        when(customerRepository.existsById(customerId)).thenReturn(true);

        // When
        boolean result = customerService.deleteCustomer(customerId);

        // Then
        assertTrue(result);
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void testUpdateCustomer() {
        // Given
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("Updated Name");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);


        // When
        Customer result = customerService.updateCustomer(customerId, updatedCustomer);

        // Then
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
    }
}

