package com.example.allicademo.dao.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByAccountNumber(String accountNumber);
}
