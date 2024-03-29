package com.healthy.repository;

import com.healthy.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, String> {
    Goal findByEmail(String email);
}
