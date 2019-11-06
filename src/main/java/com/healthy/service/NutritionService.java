package com.healthy.service;

import java.util.List;

import com.healthy.entity.Weight;

public interface NutritionService {
	List<Weight> findWeightByPeriodTime(Long startDate, Long endDate, Long accountId);
	Weight saveWeight(Weight weight);
	Weight findByDate(Long date, Long accountId);
	Weight deleteWeightByDate(Long date,Long accountId);
}
