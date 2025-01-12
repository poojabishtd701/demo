package com.example.allicademo.controller;

import com.example.allicademo.domain.Transaction;
import com.example.allicademo.service.impl.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void testCreateTransaction() throws Exception {
        // Create a mock transaction object
        Transaction transaction = new Transaction();
        transaction.setAccountNumber("12345");
        transaction.setAmount(500.0);
        transaction.setTransactionType("Credit");
        transaction.setTransactionDate(LocalDateTime.now());

        // Mock the service layer call
        when(transactionService.createTransaction(any(Transaction.class))).thenReturn(transaction);

        // Perform POST request to create a transaction
        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                         .andExpect(status().isCreated()); // Assert HTTP Status 201 Created


        // Verify if the service method was called
        verify(transactionService, times(1)).createTransaction(any(Transaction.class));
    }

    @Test
    public void testGetTransactionsByAccountNumber() throws Exception {
        // Create a list of mock transactions
        Transaction transaction1 = new Transaction();
        transaction1.setAccountNumber("12345");
        transaction1.setAmount(500.0);
        transaction1.setTransactionType("Credit");
        transaction1.setTransactionDate(LocalDateTime.now());

        Transaction transaction2 = new Transaction();
        transaction2.setAccountNumber("12345");
        transaction2.setAmount(200.0);
        transaction2.setTransactionType("Debit");
        transaction2.setTransactionDate(LocalDateTime.now());

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        // Mock the service layer call
        when(transactionService.getTransactionsByAccountNumber("12345")).thenReturn(transactions);

        // Perform GET request to retrieve transactions by account number
        mockMvc.perform(get("/api/v1/transactions")
                        .param("accountNumber", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Assert HTTP Status 200 OK
                .andExpect(jsonPath("$[0].accountNumber").value("12345")) // Assert accountNumber in first transaction
                .andExpect(jsonPath("$[1].amount").value(200.0)) // Assert amount in second transaction
                .andExpect(jsonPath("$[1].transactionType").value("Debit")); // Assert transaction type in second transaction

        // Verify if the service method was called
        verify(transactionService, times(1)).getTransactionsByAccountNumber("12345");
    }
}

