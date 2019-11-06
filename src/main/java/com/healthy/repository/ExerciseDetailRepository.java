package com.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.entity.ExerciseDetail;

@Repository
public interface ExerciseDetailRepository extends JpaRepository<ExerciseDetail, Long> {
	ExerciseDetail findByExerciseDetailId(Long exerciseDetailId);
}
