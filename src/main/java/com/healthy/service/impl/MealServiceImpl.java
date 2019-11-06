package com.healthy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthy.entity.Meal;
import com.healthy.entity.MealDetail;
import com.healthy.repository.MealDetailRepository;
import com.healthy.repository.MealRepository;
import com.healthy.service.MealService;

@Service
public class MealServiceImpl implements MealService{
  @Autowired
  private MealRepository mealRepository;
  
  @Autowired
  private MealDetailRepository mealDetailRepository;

  @Override
  public Meal createMeal(Meal meal) {
    return mealRepository.save(meal);
  }

  @Override
  public Meal updateMeal(Meal meal) {
    return mealRepository.save(meal);
  }

  @Override
  public List<MealDetail> createMealDetailList(List<MealDetail> mealDetailList) {
    return mealDetailRepository.saveAll(mealDetailList);
  }

}
