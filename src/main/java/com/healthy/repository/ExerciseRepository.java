package com.healthy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthy.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	Long removeByExerciseId(Long exerciseId);

	Exercise findByExerciseId(Long exerciseId);

	@Query(value = "FROM Exercise e WHERE (e.account.accountId = :accountId OR e.owner = 'admin') AND status = 1")
	List<Exercise> findByAccountId(@Param("accountId") Long accountId);

	@Query(value = "FROM Exercise e WHERE LOWER(e.exerciseName) LIKE LOWER(CONCAT('%',:exerciseName,'%')) AND e.account.accountId = :accountId")
	List<Exercise> searchExercise(@Param("exerciseName") String exerciseName,@Param("accountId") Long accountId);
}
