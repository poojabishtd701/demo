package com.example.allicademo.mapper;

import com.example.allicademo.dao.entity.TransactionEntity;
import com.example.allicademo.domain.Transaction;
import java.time.LocalDateTime;

public class TransactionMapper {

    public static Transaction convertToDomain(TransactionEntity transactionEntity) {
        // Map  Entity  to domain
        Transaction dto = new Transaction();
        dto.setId(transactionEntity.getId());
        dto.setAccountNumber(transactionEntity.getAccountNumber());
        dto.setAmount(transactionEntity.getAmount());
        dto.setTransactionType(transactionEntity.getType());
        dto.setTransactionDate(transactionEntity.getTransactionDate());
        return dto;
    }
    public static TransactionEntity convertToEntity(Transaction transaction) {
        // Map domain to Entity
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAccountNumber(transaction.getAccountNumber());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setType(transaction.getTransactionType());
        transactionEntity.setTransactionDate(LocalDateTime.now());
        return transactionEntity;
    }
}
