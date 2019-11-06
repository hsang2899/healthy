package com.healthy.service.impl;

import com.healthy.entity.Food;
import com.healthy.repository.FoodRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthy.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    FoodRepository foodRepository;

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food getFood(Long foodId) {
      return foodRepository.findByFoodId(foodId);
    }

    @Override
    public List<Food> searchFood(String foodName, Long accountId) {
      return foodRepository.findByFoodName(foodName,accountId);
    }

    @Override
    public List<Food> allFood() {
        return foodRepository.findAll();
    }

    @Override
    public Food updateFood(Food food) {
      return foodRepository.save(food);
    }
}
