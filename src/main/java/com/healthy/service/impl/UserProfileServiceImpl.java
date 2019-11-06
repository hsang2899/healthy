package com.healthy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.entity.UserProfile;
import com.healthy.repository.UserProfileRepository;
import com.healthy.repository.WeightRepository;
import com.healthy.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	WeightRepository weightRepository;

	@Override
	public UserProfile save(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	@Override
	public UserProfile findByAccountId(Long accountId) {
		return userProfileRepository.findByAccountAccountId(accountId);
	}

}
