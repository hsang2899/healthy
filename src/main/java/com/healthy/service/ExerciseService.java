package com.healthy.service;

import java.util.List;

import com.healthy.entity.Exercise;
import com.healthy.entity.ExerciseDay;
import com.healthy.entity.ExerciseDetail;

public interface ExerciseService {
	Exercise save(Exercise exercise);

	List<ExerciseDetail> saveDetailList(List<ExerciseDetail> exerciseDetails);

	ExerciseDay saveDay(ExerciseDay exerciseDay);

	Exercise getById(Long exerciseId);

	List<Exercise> getAll(Long accountId);

	List<Exercise> all();

	Exercise deleteById(Long exerciseId);

	List<Exercise> searchExercise(String exerciseName, Long accountId);
}
