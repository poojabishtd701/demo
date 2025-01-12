package com.example.allicademo.service.impl;

import com.example.allicademo.dao.entity.CustomerEntity;
import com.example.allicademo.dao.entity.CustomerRepository;
import com.example.allicademo.domain.Customer;
import com.example.allicademo.mapper.CustomerMapper;
import com.example.allicademo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity customerEntity =CustomerMapper.convertToEntity(customer);


        // Save and map back to DTO
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
        return  CustomerMapper.convertToDomain(savedCustomerEntity);
    }



    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id).map(CustomerMapper::convertToDomain);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Customer updateCustomer(Long id, Customer customerDetails) {
        // Fetch the existing customer
        CustomerEntity existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        if (customerDetails.getName() != null) {
            existingCustomer.setName(customerDetails.getName());
        }
        CustomerEntity updatedCustomerEntity = customerRepository.save(existingCustomer);
        return CustomerMapper.convertToDomain(updatedCustomerEntity);
    }

}
