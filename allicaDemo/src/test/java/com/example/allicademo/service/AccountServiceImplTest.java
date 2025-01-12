package com.example.allicademo.service;


import com.example.allicademo.dao.entity.AccountEntity;
import com.example.allicademo.dao.entity.AccountRepository;
import com.example.allicademo.domain.Account;
import com.example.allicademo.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class AccountServiceImplTest {


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;
    private AccountEntity accountEntity;

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
        account.setAccountNumber("1234567");
        account.setBalance(2000.0);
        account.setCustomerId(8L);
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGetAccountsByCustomerId() {

        Long customerId = 1L;
        List<AccountEntity> accountEntities = Collections.singletonList(accountEntity);
        when(accountRepository.findByCustomerId(customerId)).thenReturn(accountEntities);
        // act
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        // assert
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals("123456789", accounts.get(0).getAccountNumber());
        verify(accountRepository, times(1)).findByCustomerId(customerId);
    }

    @Test
    void testCreateAccount() {
        // Given
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountEntity);

        // act
        Account createdAccount = accountService.createAccount(account);

        // assert
        assertNotNull(createdAccount);
        assertEquals("123456789", createdAccount.getAccountNumber());
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
    }
}

