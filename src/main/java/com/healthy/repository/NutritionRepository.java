package com.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healthy.entity.Nutrition;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
	
}
