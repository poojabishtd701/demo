package com.example.allicademo.dao.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")

public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "transaction_type", nullable = false)
    private String type; // Credit or Debit
    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
