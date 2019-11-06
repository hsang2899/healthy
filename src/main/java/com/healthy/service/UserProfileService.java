package com.healthy.service;

import java.util.List;

import com.healthy.entity.UserProfile;
import com.healthy.entity.Weight;

public interface UserProfileService {
  UserProfile save(UserProfile userProfile);
  UserProfile findByAccountId(Long accountId);
}
