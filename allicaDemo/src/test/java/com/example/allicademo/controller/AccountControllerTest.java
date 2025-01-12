package com.example.allicademo.controller;


import com.example.allicademo.domain.Account;
import com.example.allicademo.service.impl.AccountServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountServiceImpl accountService;

    @InjectMocks
    private AccountController accountController;

    private Account account1;
    private Account account2;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        // Initialize the Account objects using setters
        account1 = new Account();
        account1.setId(1L);
        account1.setAccountNumber("12345");
        account1.setBalance(1000.00);
        account1.setCustomerId(1L);

        account2 = new Account();
        account2.setId(2L);
        account2.setAccountNumber("67890");
        account2.setBalance(1500.00);
        account2.setCustomerId(1L);

        MockitoAnnotations.openMocks(this);
        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void testGetAccountsByCustomerId() throws Exception {
        // Mock the service layer
        when(accountService.getAccountsByCustomerId(1L)).thenReturn(Arrays.asList(account1, account2));

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts?customerId=1"))
                .andExpect(status().isOk()) // Assert HTTP Status 200 OK
                .andExpect(jsonPath("$.size()").value(2)) // Assert that two accounts are returned
                .andExpect(jsonPath("$[0].accountNumber").value("12345")) // Assert account 1 number
                .andExpect(jsonPath("$[1].accountNumber").value("67890")); // Assert account 2 number

        // Verify if the service method was called
        verify(accountService, times(1)).getAccountsByCustomerId(1L);
    }

    @Test
    void testCreateAccount() throws Exception {
        // Mock the service layer
        when(accountService.createAccount(account1)).thenReturn(account1);

        // Perform POST request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account1)))
                .andExpect(status().isCreated()) ;// Assert HTTP Status 201 Created

        // Verify if the service method was called
        verify(accountService, times(1)).createAccount(any(Account.class));
    }


}
