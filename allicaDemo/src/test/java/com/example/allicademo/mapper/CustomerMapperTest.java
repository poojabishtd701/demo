package com.example.allicademo.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.example.allicademo.dao.entity.CustomerEntity;
import com.example.allicademo.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerMapperTest {

    private CustomerEntity customerEntity;
    private Customer customer;

    @BeforeEach
    void setUp() {
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
    void testConvertToDomain() {
        // Call the method to convert from CustomerEntity to Customer
        Customer convertedCustomer = CustomerMapper.convertToDomain(customerEntity);

        // Assert that the Customer object is not null
        assertNotNull(convertedCustomer);

        // Assert the values are correctly mapped from CustomerEntity to Customer
        assertEquals(customerEntity.getId(), convertedCustomer.getId());
        assertEquals(customerEntity.getName(), convertedCustomer.getName());
    }

    @Test
    void testConvertToEntity() {
        // Call the method to convert from Customer to CustomerEntity
        CustomerEntity convertedCustomerEntity = CustomerMapper.convertToEntity(customer);

        // Assert that the CustomerEntity object is not null
        assertNotNull(convertedCustomerEntity);

        // Assert the values are correctly mapped from Customer to CustomerEntity
        assertEquals(customer.getName(), convertedCustomerEntity.getName());
    }
}

