package com.healthy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.entity.Exercise;
import com.healthy.entity.ExerciseDay;
import com.healthy.entity.ExerciseDetail;
import com.healthy.repository.ExerciseDayRepository;
import com.healthy.repository.ExerciseDetailRepository;
import com.healthy.repository.ExerciseRepository;
import com.healthy.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	private ExerciseDetailRepository exerciseDetailRepository;

	@Autowired
	private ExerciseDayRepository exerciseDayRepository;

	@Override
	public Exercise save(Exercise exercise) {
		return exerciseRepository.save(exercise);
	}

	@Override
	public List<ExerciseDetail> saveDetailList(List<ExerciseDetail> exerciseDetails) {
		return exerciseDetailRepository.saveAll(exerciseDetails);
	}

	@Override
	public ExerciseDay saveDay(ExerciseDay exerciseDay) {
		return exerciseDayRepository.save(exerciseDay);
	}

	@Override
	public Exercise getById(Long exerciseId) {
		return exerciseRepository.findByExerciseId(exerciseId);
	}

	@Override
	public List<Exercise> getAll(Long accountId) {
		return exerciseRepository.findByAccountId(accountId);
	}

	@Override
	public List<Exercise> all() {
		return exerciseRepository.findAll();
	}

	@Override
	public Exercise deleteById(Long exerciseId) {
		Exercise exercise = exerciseRepository.findByExerciseId(exerciseId);
		exerciseRepository.delete(exercise);
		return exercise;
	}

	@Override
	public List<Exercise> searchExercise(String exerciseName, Long accountId) {
		return exerciseRepository.searchExercise(exerciseName, accountId);
	}

}
