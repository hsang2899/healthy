package com.healthy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.healthy.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
  Long removeByFoodId(Long foodId);
  
  Food findByFoodId(Long foodId);
  
  @Query(value = "FROM Food f WHERE LOWER(f.foodName) LIKE LOWER(CONCAT('%',:foodName,'%')) AND status = 1 AND (account_id = :accountId OR owner = 'admin')")
  List<Food> findByFoodName(@Param("foodName") String foodName,@Param("accountId") Long accountId);
}
