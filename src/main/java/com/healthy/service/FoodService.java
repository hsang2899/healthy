package com.healthy.service;

import java.util.List;
import com.healthy.entity.Food;

public interface FoodService {
    Food createFood(Food food);
    
    Food getFood(Long foodId);
    
    List<Food> searchFood(String foodName, Long accountId);

    List<Food> allFood();
    
    Food updateFood(Food food);
}
