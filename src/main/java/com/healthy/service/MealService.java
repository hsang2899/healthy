package com.healthy.service;

import java.util.List;
import com.healthy.entity.Meal;
import com.healthy.entity.MealDetail;

public interface MealService {
  Meal createMeal(Meal meal);
  Meal updateMeal(Meal meal);
  List<MealDetail> createMealDetailList(List<MealDetail> mealDetailList);
}
