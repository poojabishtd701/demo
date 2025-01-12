package com.example.allicademo.service.impl;

import com.example.allicademo.dao.entity.AccountEntity;
import com.example.allicademo.dao.entity.AccountRepository;
import com.example.allicademo.domain.Account;
import com.example.allicademo.mapper.AccountMapper;
import com.example.allicademo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        List<AccountEntity> accountEntities = accountRepository.findByCustomerId(customerId);

        // Convert Entity to Domain
        return accountEntities.stream()
                .map(AccountMapper::convertToDomain)
                .collect(Collectors.toList());
    }



    @Override
    public Account createAccount(Account account) {
        // Convert Domain to Entity
        AccountEntity accountEntity = AccountMapper. convertToEntity(account);

        // Save to database
        AccountEntity savedAccountEntity = accountRepository.save(accountEntity);

        // Convert saved Entity to Domain and return
        return AccountMapper. convertToDomain(savedAccountEntity);
    }


}
