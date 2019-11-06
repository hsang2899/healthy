package com.healthy.service;

import com.healthy.entity.Account;

public interface AccountService {
  Account createAccount(Account account);
  Account updateAccount(Account account);
}
