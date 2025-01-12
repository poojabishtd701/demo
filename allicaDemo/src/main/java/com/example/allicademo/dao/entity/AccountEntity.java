package com.example.allicademo.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")

public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private Double balance;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
