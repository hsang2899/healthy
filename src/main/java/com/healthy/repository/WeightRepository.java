package com.healthy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthy.entity.Weight;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
	@Query(value = "SELECT * FROM weight WHERE date >= :startDate AND date <= :endDate AND account_id = :accountId", nativeQuery = true)
	List<Weight> findByPeriodTime(@Param("startDate") Long startDate, @Param("endDate") Long endDate,
			@Param("accountId") Long accountId);

	Weight findByDateAndAccountAccountId(Long date, Long accountId);

	@Modifying
	@Query(value = "UPDATE  user_profile SET weight = (SELECT w.weight FROM weight w WHERE w.account_id = :accountId ORDER BY date DESC LIMIT 1)", nativeQuery = true)
	Integer updateWeightUserProfile(@Param("accountId") Long accountId);
}
