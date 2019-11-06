package com.healthy.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.entity.Account;
import com.healthy.entity.UserProfile;
import com.healthy.model.DTOEntity;
import com.healthy.model.DtoUtils;
import com.healthy.model.ResponseObject;
import com.healthy.model.UserProfileCreateDto;
import com.healthy.repository.AccountRepository;
import com.healthy.service.AccountService;
import com.healthy.service.UserProfileService;

@RestController
@RequestMapping(value = "/api/user-profiles")
public class UserProfileController {
	Logger logger = Logger.getLogger(UserProfileController.class.getName());
	@Autowired
	UserProfileService userProfileService;

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountService accountService;

	@PostMapping("/create")
	public ResponseObject<DTOEntity> createUserProfile(@RequestBody UserProfileCreateDto userProfileCreateDto,
			HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		logger.info("rsEmail: " + rsEmail);
		if (rsEmail == null || rsEmail.equals("")) {
			return new ResponseObject<>(null, false, "Error");
		}
		Account account = accountRepository.findByEmail(rsEmail);
		if (account == null) {
			return new ResponseObject<>(null, false, "Error");
		}

		UserProfile userProfile = userProfileService.findByAccountId(account.getAccountId());
		if (null != userProfile) {
			userProfile.setAvatarUrl(userProfileCreateDto.getAvatarUrl());
			userProfile.setDateOfBirth(userProfileCreateDto.getDateOfBirth());
			userProfile.setFullName(userProfileCreateDto.getFullName());
			userProfile.setGender(userProfileCreateDto.getGender());
			userProfile.setHeight(userProfileCreateDto.getHeight());
			userProfile.setPhone(userProfileCreateDto.getPhone());
		} else {
			userProfile = (UserProfile) DtoUtils.convertToEntity(new UserProfile(), userProfileCreateDto);
			userProfile.setAccount(account);
			userProfile.setUserProfileId(null);
			userProfile.setCreatedAt(System.currentTimeMillis());
		}
		userProfile.setUpdatedAt(System.currentTimeMillis());
		UserProfile newUserProfile = userProfileService.save(userProfile);
		UserProfileCreateDto newUserProfileDto = (UserProfileCreateDto) DtoUtils.convertToDto(newUserProfile,
				new UserProfileCreateDto());
		return new ResponseObject<>(newUserProfileDto, true, "Create user profile successfully.");
	}

	@GetMapping
	public ResponseObject<DTOEntity> getUserProfile(HttpServletRequest request) {
		Account account = accountRepository.findByEmail(request.getAttribute("rsEmail").toString());
		UserProfileCreateDto newUserProfileDto = (UserProfileCreateDto) DtoUtils
				.convertToDto(userProfileService.findByAccountId(account.getAccountId()), new UserProfileCreateDto());
		return new ResponseObject<>(newUserProfileDto, true, "Get user profile successfully.");
	}
}
