package com.healthy.service;

import java.util.List;

import com.healthy.entity.Day;
import com.healthy.entity.ExerciseDetail;
import com.healthy.entity.MealDetail;
import com.healthy.model.DayGetDto;

public interface DayService {
	Day createDay(Day day);

	List<DayGetDto> getAllDay(Long accountId);

	Day getById(Long accountId, Long dayId);
	
	Day findByDate(Long date,Long accountId);

	DayGetDto getDayByDate(Long accountId, Long date);

	ExerciseDetail deleteExerciseDetail(Long exerciseDetailId);
	
	MealDetail deleteMealDetail(Long mealDetailId);
	
	MealDetail getMealDetailById(Long mealDetailId);
	
	MealDetail saveMealDetail(MealDetail mealDetail);
	
}
