package com.example.allicademo.service.impl;

import com.example.allicademo.dao.entity.AccountEntity;
import com.example.allicademo.dao.entity.TransactionEntity;
import com.example.allicademo.dao.entity.TransactionRepository;
import com.example.allicademo.domain.Transaction;
import com.example.allicademo.exception.ResourceNotFoundException;
import com.example.allicademo.mapper.TransactionMapper;
import com.example.allicademo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByAccountNumber(accountNumber);
        if (transactionEntities.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for account number: " + accountNumber);
        }
        return transactionEntities.stream()
                .map(TransactionMapper::convertToDomain)
                .collect(Collectors.toList());
    }

    public Transaction createTransaction(Transaction Transaction) {
      TransactionEntity transactionEntity = TransactionMapper.convertToEntity(Transaction);
      TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);
      Transaction createdTransaction = TransactionMapper.convertToDomain(savedTransaction);
      return createdTransaction;
    }


}
