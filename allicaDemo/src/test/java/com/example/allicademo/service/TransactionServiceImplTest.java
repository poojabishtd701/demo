package com.example.allicademo.service;


import com.example.allicademo.dao.entity.TransactionEntity;
import com.example.allicademo.dao.entity.TransactionRepository;
import com.example.allicademo.domain.Transaction;
import com.example.allicademo.exception.ResourceNotFoundException;
import com.example.allicademo.mapper.TransactionMapper;
import com.example.allicademo.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Transaction transaction;
    private TransactionEntity transactionEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup a TransactionEntity object for the tests
        transactionEntity = new TransactionEntity();
        transactionEntity.setId(1L);
        transactionEntity.setAccountNumber("123456");
        transactionEntity.setAmount(1000.0);
        transactionEntity.setType("DEBIT");

        // Setup a Transaction object for the tests
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccountNumber("123456");
        transaction.setAmount(1000.0);
        transaction.setTransactionType("DEBIT");
    }

    @Test
    void testGetTransactionsByAccountNumber() {
        // Given
        String accountNumber = "123456";
        List<TransactionEntity> transactionEntities = Arrays.asList(transactionEntity);
        when(transactionRepository.findByAccountNumber(accountNumber)).thenReturn(transactionEntities);

        // When
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);

        // Then
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals("123456", transactions.get(0).getAccountNumber());
        verify(transactionRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void testGetTransactionsByAccountNumber_NoTransactionsFound() {
        // Given
        String accountNumber = "123456";
        when(transactionRepository.findByAccountNumber(accountNumber)).thenReturn(Arrays.asList());

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            transactionService.getTransactionsByAccountNumber(accountNumber);
        });

        assertEquals("No transactions found for account number: 123456", exception.getMessage());
        verify(transactionRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void testCreateTransaction() {
        // Given
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity); // Mocking Repository's save

        // When
        Transaction createdTransaction = transactionService.createTransaction(transaction);

        // Then
        assertNotNull(createdTransaction);
        assertEquals("123456", createdTransaction.getAccountNumber());
        assertEquals(1000.0, createdTransaction.getAmount());
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class)); // Verifying the save method
    }
}

