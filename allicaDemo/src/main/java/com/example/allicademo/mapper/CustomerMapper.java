package com.example.allicademo.mapper;

import com.example.allicademo.dao.entity.CustomerEntity;
import com.example.allicademo.domain.Customer;

public class CustomerMapper {

    public static Customer convertToDomain(CustomerEntity customerEntity) {
        Customer dto = new Customer();
        dto.setId(customerEntity.getId());
        dto.setName(customerEntity.getName());
        return dto;
    }

    public static CustomerEntity convertToEntity(Customer customer) {
        // Map DTO to Entity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        return customerEntity;
    }
}
