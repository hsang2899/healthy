package com.healthy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthy.entity.Account;
import com.healthy.repository.AccountRepository;
import com.healthy.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
  @Autowired
  AccountRepository accountRepository;
  
  public Account createAccount(Account account) {
    return accountRepository.save(account);
  }
  public Account updateAccount(Account account) {
    return accountRepository.save(account);
  }
}
