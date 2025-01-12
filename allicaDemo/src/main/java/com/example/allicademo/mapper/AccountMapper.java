package com.example.allicademo.mapper;

import com.example.allicademo.dao.entity.AccountEntity;
import com.example.allicademo.domain.Account;

public class AccountMapper {

    public static Account convertToDomain(AccountEntity accountEntity) {
        Account dto = new Account();
        dto.setId(accountEntity.getId());
        dto.setAccountNumber(accountEntity.getAccountNumber());
        dto.setBalance(accountEntity.getBalance());
        dto.setCustomerId(accountEntity.getCustomerId());
        return dto;
    }

    public static AccountEntity convertToEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setCustomerId(account.getCustomerId());
        return accountEntity;
    }
}
