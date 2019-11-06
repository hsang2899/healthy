package com.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.entity.ExerciseDay;

@Repository
public interface ExerciseDayRepository extends JpaRepository<ExerciseDay, Long> {

}
