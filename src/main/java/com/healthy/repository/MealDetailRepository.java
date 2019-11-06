package com.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healthy.entity.MealDetail;

@Repository
public interface MealDetailRepository extends JpaRepository<MealDetail, Long> {
	MealDetail findByMealDetailId(Long mealDetailId);
}
