package com.healthy.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.entity.Weight;
import com.healthy.repository.WeightRepository;
import com.healthy.service.NutritionService;

@Service
public class NutritionServiceImpl implements NutritionService {
	@Autowired
	WeightRepository weightRepository;

	@Override
	public List<Weight> findWeightByPeriodTime(Long startDate, Long endDate, Long accountId) {
		return weightRepository.findByPeriodTime(startDate, endDate, accountId);
	}

	@Override
	@Transactional
	public Weight saveWeight(Weight weight) {
		Weight newWeight = weightRepository.save(weight);
		weightRepository.updateWeightUserProfile(weight.getAccount().getAccountId());
		return newWeight;
	}

	@Override
	public Weight findByDate(Long date,Long accountId) {
		return weightRepository.findByDateAndAccountAccountId(date,accountId);
	}

	@Override
	public Weight deleteWeightByDate(Long date,Long accountId) {
		Weight weight = weightRepository.findByDateAndAccountAccountId(date,accountId);
		weightRepository.delete(weight);
		return weight;
	}
}
