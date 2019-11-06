package com.healthy.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthy.entity.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.break_fast_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId", nativeQuery = true)
	List<Map<String, String>> getAllDayWithBreakFast(@Param("accountId") Long accountId);

	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.lunch_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId", nativeQuery = true)
	List<Map<String, String>> getAllDayWithLunch(@Param("accountId") Long accountId);

	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.dinner_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId", nativeQuery = true)
	List<Map<String, String>> getAllDayWithDinner(@Param("accountId") Long accountId);

	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.snacks_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId", nativeQuery = true)
	List<Map<String, String>> getAllDayWithSnacks(@Param("accountId") Long accountId);

	@Query(value = "SELECT d.day_id, d.date, e.index_of_sets, e.repetitions,e.status, eday.exercise_day_id, "
			+ "e.weight_per_set, e.calories_burn,e.exercise_name,e.video_url,e.exercise_id, d.account_id, ed.exercise_detail_id "
			+ "FROM exercise_day eday INNER JOIN exercise_detail ed ON eday.exercise_day_id = ed.exercise_day_id "
			+ "INNER JOIN day d ON d.exercise_day_id = eday.exercise_day_id INNER JOIN exercise e ON ed.exercise_id = e.exercise_id "
			+ "WHERE d.account_id = :accountId", nativeQuery = true)
	List<Map<String, String>> getAllDayWithExercise(@Param("accountId") Long accountId);

	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.break_fast_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId AND d.date = :date", nativeQuery = true)
	List<Map<String, String>> getADayWithBreakFast(@Param("accountId") Long accountId, @Param("date") Long date);

	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.lunch_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId AND d.date = :date", nativeQuery = true)
	List<Map<String, String>> getADayWithLunch(@Param("accountId") Long accountId, @Param("date") Long date);

	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.dinner_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId AND d.date = :date", nativeQuery = true)
	List<Map<String, String>> getADayWithDinner(@Param("accountId") Long accountId, @Param("date") Long date);

	@Query(value = "SELECT md.meal_detail_id, md.food_id, m.meal_id, m.status, d.day_id, d.date, md.quantity AS meal_quantity, "
			+ "f.quantity, f.calories, f.carbs, f.description, f.fat, f.food_name, f.proteins, f.unit, d.account_id "
			+ "FROM meal_detail md INNER JOIN meal m ON m.meal_id = md.meal_id "
			+ "INNER JOIN day d ON d.snacks_id = md.meal_id INNER JOIN food f ON f.food_id = md.food_id "
			+ "WHERE d.account_id = :accountId AND d.date = :date", nativeQuery = true)
	List<Map<String, String>> getADayWithSnacks(@Param("accountId") Long accountId, @Param("date") Long date);

	@Query(value = "SELECT d.day_id, d.date, e.index_of_sets, e.repetitions,e.status, eday.exercise_day_id, "
			+ "e.weight_per_set, e.calories_burn,e.exercise_name,e.video_url,e.exercise_id, d.account_id, ed.exercise_detail_id "
			+ "FROM exercise_day eday INNER JOIN exercise_detail ed ON eday.exercise_day_id = ed.exercise_day_id "
			+ "INNER JOIN day d ON d.exercise_day_id = eday.exercise_day_id INNER JOIN exercise e ON ed.exercise_id = e.exercise_id "
			+ "WHERE d.account_id = :accountId AND d.date = :date", nativeQuery = true)
	List<Map<String, String>> getADayWithExercise(@Param("accountId") Long accountId, @Param("date") Long date);

	Day findByDayIdAndAccountAccountId(Long dayId, Long accountId);

	Day findByDateAndAccountAccountId(Long date, Long accountId);
}
