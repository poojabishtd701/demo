package com.example.allicademo.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;
import com.example.allicademo.dao.entity.TransactionEntity;
import com.example.allicademo.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionMapperTest {

    private TransactionEntity transactionEntity;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        // Setup a TransactionEntity object for the tests
        transactionEntity = new TransactionEntity();
        transactionEntity.setId(1L);
        transactionEntity.setAccountNumber("123456789");
        transactionEntity.setAmount(500.00);
        transactionEntity.setType("Credit");
        transactionEntity.setTransactionDate(LocalDateTime.now());

        // Setup a Transaction object for the tests
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccountNumber("123456789");
        transaction.setAmount(500.00);
        transaction.setTransactionType("Credit");
        transaction.setTransactionDate(LocalDateTime.now());
    }

    @Test
    void testConvertToDomain() {
        Transaction convertedTransaction = TransactionMapper.convertToDomain(transactionEntity);

        // Assert that the Transaction object is not null
        assertNotNull(convertedTransaction);

        // Assert the values are correctly mapped from TransactionEntity to Transaction
        assertEquals(transactionEntity.getId(), convertedTransaction.getId());
        assertEquals(transactionEntity.getAccountNumber(), convertedTransaction.getAccountNumber());
        assertEquals(transactionEntity.getAmount(), convertedTransaction.getAmount());
        assertEquals(transactionEntity.getType(), convertedTransaction.getTransactionType());
        assertEquals(transactionEntity.getTransactionDate(), convertedTransaction.getTransactionDate());
    }

    @Test
    void testConvertToEntity() {
        TransactionEntity convertedTransactionEntity = TransactionMapper.convertToEntity(transaction);

        // Assert that the TransactionEntity object is not null
        assertNotNull(convertedTransactionEntity);

        // Assert the values are correctly mapped from Transaction to TransactionEntity
        assertEquals(transaction.getAccountNumber(), convertedTransactionEntity.getAccountNumber());
        assertEquals(transaction.getAmount(), convertedTransactionEntity.getAmount());
        assertEquals(transaction.getTransactionType(), convertedTransactionEntity.getType());
        assertNotNull(convertedTransactionEntity.getTransactionDate());
    }
}

