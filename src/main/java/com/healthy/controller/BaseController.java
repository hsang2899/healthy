package com.healthy.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.healthy.model.AccountDto;

public class BaseController {
	AccountDto getCurrentAccountDtoByToken() {
		return (AccountDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
