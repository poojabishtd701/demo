package com.example.allicademo.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.example.allicademo.dao.entity.AccountEntity;
import com.example.allicademo.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountMapperTest {

    private AccountEntity accountEntity;
    private Account account;

    @BeforeEach
    void setUp() {
        // Setup an AccountEntity object for the tests
        accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setAccountNumber("123456789");
        accountEntity.setBalance(5000.0);
        accountEntity.setCustomerId(10L);

        // Setup an Account object for the tests
        account = new Account();
        account.setId(1L);
        account.setAccountNumber("123456789");
        account.setBalance(5000.0);
        account.setCustomerId(10L);
    }

    @Test
    void testConvertToDomain() {
        // Call the method to convert from AccountEntity to Account
        Account convertedAccount = AccountMapper.convertToDomain(accountEntity);

        // Assert that the Account object is not null
        assertNotNull(convertedAccount);

        // Assert the values are correctly mapped from AccountEntity to Account
        assertEquals(accountEntity.getId(), convertedAccount.getId());
        assertEquals(accountEntity.getAccountNumber(), convertedAccount.getAccountNumber());
        assertEquals(accountEntity.getBalance(), convertedAccount.getBalance());
        assertEquals(accountEntity.getCustomerId(), convertedAccount.getCustomerId());
    }

    @Test
    void testConvertToEntity() {
        // Call the method to convert from Account to AccountEntity
        AccountEntity convertedAccountEntity = AccountMapper.convertToEntity(account);

        // Assert that the AccountEntity object is not null
        assertNotNull(convertedAccountEntity);

        // Assert the values are correctly mapped from Account to AccountEntity
        assertEquals(account.getAccountNumber(), convertedAccountEntity.getAccountNumber());
        assertEquals(account.getBalance(), convertedAccountEntity.getBalance());
        assertEquals(account.getCustomerId(), convertedAccountEntity.getCustomerId());
    }
}
