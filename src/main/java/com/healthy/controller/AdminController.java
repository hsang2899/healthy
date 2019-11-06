package com.healthy.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.entity.Account;
import com.healthy.model.AccountDto;
import com.healthy.model.ResponseObject;
import com.healthy.repository.AccountRepository;
import com.healthy.service.AccountService;

@RestController(value = "AdminController")
public class AdminController {
    Logger logger = Logger.getLogger(AdminController.class.getName());
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @PostMapping(value = "/admin/register")
    @ResponseBody
    public ResponseObject<Account> register(@RequestBody AccountDto accountDto, HttpServletRequest request) {
        logger.info("register admin.");
        String rsEmail = request.getAttribute("rsEmail").toString();
        logger.info("rsEmail: " + rsEmail);
        Account adminAccount = accountRepository.findByEmail(rsEmail);
        logger.info("role admin: " + adminAccount.getRole());
        if(!adminAccount.getRole().equals("admin")) {
            return new ResponseObject<>(null, false, "error forbidden!");
        }
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account );
        // Account account = (Account) DtoUtils.convertToEntity(new Account(), accountDto);
        logger.info("email: " + account.getEmail());
        logger.info("role set: " + account.getRole());
        Account accountCheck = accountRepository.findByEmail(account.getEmail());
        if (accountCheck != null) {
            accountCheck.setRole(account.getRole());
            accountService.updateAccount(accountCheck);
            return new ResponseObject<>(accountCheck, true, "new admin success!");
        } else {
            Account newAccount = accountService.createAccount(account);
            return new ResponseObject<>(newAccount, true, "new admin success!");
        }
    }
}
