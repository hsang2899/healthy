package com.healthy.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.healthy.api.AccountApi;
import com.healthy.api.LoginForm;
import com.healthy.config.SecurityTokenConfig;
import com.healthy.entity.Account;
import com.healthy.entity.UserProfile;
import com.healthy.model.AccountDto;
import com.healthy.model.DtoUtils;
import com.healthy.model.ResponseObject;
import com.healthy.repository.AccountRepository;
import com.healthy.repository.UserProfileRepository;
import com.healthy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Logger;

@RestController(value = "AccController")
public class AccController {
    Logger logger = Logger.getLogger(AccController.class.getName());
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserProfileRepository userProfileRepository;

    @PostMapping(value = "/account/login")
    @ResponseBody
    public LoginForm.Response login(@RequestBody LoginForm.Request loginForm) {
        System.out.println("email : " + loginForm.getEmail() + " password :" + loginForm.getPassword());

        Account account = accountRepository.findByEmail(loginForm.getEmail());
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, loginForm.getEmail() + " Not Found");
        }

        if (loginForm.getPassword().equals(account.getPassword())) {
            Algorithm algorithm = Algorithm.HMAC256(SecurityTokenConfig.TOKEN_SECRET);
            UserProfile user = userProfileRepository.findByAccountAccountId(account.getAccountId());
            LoginForm.Response rest = new LoginForm.Response();
            logger.info("user: " + user);
            if(user == null) {
                rest.setInformation(false);
            } else {
                rest.setInformation(true);
            }
            rest.setRole(account.getRole());
            rest.setToken(JWT.create().withJWTId(account.getEmail()).withIssuer(SecurityTokenConfig.TOKEN_ISSUER).sign(algorithm));
            return rest;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    loginForm.getEmail() + " Not Found or wrong password");
        }
    }

    @PostMapping(value = "/account/register")
    @ResponseBody
    public ResponseObject<Account> register(@RequestBody AccountDto accountDto) {
        System.out.println("register account.");
        Account account = (Account) DtoUtils.convertToEntity(new Account(), accountDto);
        System.out.println("email: " + account.getEmail());
        System.out.println("user name request: " + account.getUserName());
        Account accountCheck = accountRepository.findByEmail(account.getEmail());
        if (accountCheck != null) {
           // throw new ResponseStatusException(HttpStatus.CONFLICT, "account already exists");
            return new ResponseObject<>(null, false, "account already exists!");
        }
        account.setStatus("active");
        account.setRole("user");
        account.setCreatedAt(System.currentTimeMillis());
        account.setUpdatedAt(System.currentTimeMillis());
        Account newAccount = accountService.createAccount(account);

        return new ResponseObject<>(newAccount, true, "account register success!");
    }

    @GetMapping(value = "/account/my-account")
    @ResponseBody
    public AccountApi.Response myAccount(HttpServletRequest request) {
        System.out.println("get my account");
        String rsEmail = request.getAttribute("rsEmail").toString();
        logger.info("rsEmail: " + rsEmail);
        Account account = accountRepository.findByEmail(rsEmail);
        logger.info("account: " + account);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.FOUND, "account not exists or delted");
        }
        AccountApi.Response rest = new AccountApi.Response();
        rest.setEmail(account.getEmail());
        rest.setUserName(account.getUserName());
        rest.setRole(account.getRole());
        rest.setCreatedAt(account.getCreatedAt());
        rest.setUpdatedAt(account.getUpdatedAt());
        return rest;
    }
}
