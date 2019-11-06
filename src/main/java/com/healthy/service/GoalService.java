package com.healthy.service;

import com.healthy.entity.Goal;

import java.util.List;

public interface GoalService {
    Goal createGoal(Goal goal);

    Goal getGoal(String email);

    List<Goal> searchGoal();
}
